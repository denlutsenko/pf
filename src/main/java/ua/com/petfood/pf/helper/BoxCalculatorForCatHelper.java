package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.FoodType;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.model.dto.response.recommendedbox.Box;
import ua.com.petfood.pf.service.DailyFoodAmountService;
import ua.com.petfood.pf.service.FoodTypeService;
import ua.com.petfood.pf.service.SKUItemService;

import java.util.*;

@Component
public class BoxCalculatorForCatHelper extends BoxCalculatorHelper {

    private FoodTypeService foodTypeService;
    private DailyFoodAmountService dailyFoodAmountService;
    private SKUItemService skuItemService;

    public BoxCalculatorForCatHelper(FoodTypeService foodTypeService, DailyFoodAmountService dailyFoodAmountService,
            SKUItemService skuItemService) {
        this.foodTypeService = foodTypeService;
        this.dailyFoodAmountService = dailyFoodAmountService;
        this.skuItemService = skuItemService;
    }

    public List<Box> calculateRecommendedBoxForCat(final QuestionnaireDTO questDto,
            final AnimalCategory animalCategory) {
        List<Box> boxes = new ArrayList<>();

        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = adjustPreferableFood(preferableFoodId.intValue());
        String animalAgeType = adjustAnimalAgeType(questDto.getAge());
        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        if(preferableFood == null) {
            for(String brand : skuBrandsByPetCategory) {
                boxes.addAll(
                        calculateRecommendedBoxWithMixedItems(animalCategory, animalAgeType, purchaseFrequency, brand));
            }
        } else {
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos = adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount);

            for(String brand : skuBrandsByPetCategory) {
                Box box = new Box();
                box.getLineItems()
                        .add(createRecommendedBoxForCat(animalCategory.getId(), animalAgeType, preferableFoodId,
                                severalDaysFoodAmountKilos, brand));
                box.setBrand(brand);
                boxes.add(box);
            }
        }

        return boxes;
    }

    private List<Box> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
            final String animalAgeType, final int purchaseFrequency, String brand) {
        List<Box> result = new ArrayList<>();
        Box box = new Box();
        for(FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos =
                    adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount) / 2;

            box.getLineItems().add(createRecommendedBoxForCat(animalCategory.getId(), animalAgeType,
                    foodTypeId, severalDaysFoodAmountKilos, brand));
            box.setBrand(brand);
            result.add(box);
        }

        return result;
    }

    private  Map<String, Object> createRecommendedBoxForCat(final Long animalCategoryId, final String animalAgeType,
            final Long preferableFoodId, final double severalDaysFoodAmountKilos, String brand) {

        double closestSKUWeight = skuItemService
                .findClosestSKUWeightForCat(brand, severalDaysFoodAmountKilos, animalAgeType, preferableFoodId,
                        animalCategoryId);

        SKUItem skuItem = skuItemService
                .findRecommendedSKUItemForCat(animalCategoryId, brand, animalAgeType, preferableFoodId,
                        closestSKUWeight, true);

        if(skuItem == null) {
            skuItem = skuItemService
                    .findRecommendedSKUItemForCat(animalCategoryId, brand, animalAgeType, preferableFoodId,
                            closestSKUWeight, false);
        }

        return adjustRecommendedSKUWItems(severalDaysFoodAmountKilos, skuItem);
    }

    // норма еды в день для животного
    private int adjustFoodAmountForOneDay(final String animalCategory, final String animalAgeType,
            final String preferableFood) {

        return Optional.ofNullable(
                dailyFoodAmountService.getDailyFoodAmountForCat(animalCategory, animalAgeType, preferableFood))
                .orElseThrow(() -> new NotFoundException("Can't find daily food amount value"));
    }

    // тип корма (сухой, жидкий, смешанный)
    private String adjustPreferableFood(final int code) {
        return foodTypeService.getFoodTypeByCode(code);
    }
}
