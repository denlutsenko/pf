package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.FoodType;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
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

    public Map<String, List<Map>> calculateRecommendedBoxForCat(final QuestionnaireDTO questDto,
            final AnimalCategory animalCategory) {
        Map<String, List<Map>> result = new HashMap<>();

        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = adjustPreferableFood(preferableFoodId.intValue());
        String animalAgeType = adjustAnimalAgeType(questDto.getAge());
        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        if(preferableFood == null) {
            for(String brand : skuBrandsByPetCategory) {
                result.put(brand,
                        calculateRecommendedBoxWithMixedItems(animalCategory, animalAgeType, purchaseFrequency, brand));
            }
        } else {
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos = adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount);

            for(String brand : skuBrandsByPetCategory) {
                List<Map> recommendedBoxForCat = List
                        .of(createRecommendedBoxForCat(animalCategory.getId(), animalAgeType, preferableFoodId,
                                severalDaysFoodAmountKilos, brand));
                result.put(brand, recommendedBoxForCat);
            }
        }

        return result;
    }

    private List<Map> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
            final String animalAgeType, final int purchaseFrequency, String brand) {
        List<Map> result = new ArrayList<>();
        for(FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos =
                    adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount) / 2;
            Map<String, Object> recommendedMixedBox = createRecommendedBoxForCat(animalCategory.getId(), animalAgeType,
                    foodTypeId, severalDaysFoodAmountKilos, brand);

            result.add(recommendedMixedBox);
        }
        return result;
    }

    private Map<String, Object> createRecommendedBoxForCat(final Long animalCategoryId, final String animalAgeType,
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

        return adjustRecommendedSKUWeight(severalDaysFoodAmountKilos, skuItem);
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




