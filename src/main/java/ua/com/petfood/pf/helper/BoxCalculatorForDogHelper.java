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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class BoxCalculatorForDogHelper extends BoxCalculatorHelper {
    private FoodTypeService foodTypeService;
    private DailyFoodAmountService dailyFoodAmountService;
    private SKUItemService skuItemService;

    public BoxCalculatorForDogHelper(FoodTypeService foodTypeService, DailyFoodAmountService dailyFoodAmountService,
            SKUItemService skuItemService) {
        this.foodTypeService = foodTypeService;
        this.dailyFoodAmountService = dailyFoodAmountService;
        this.skuItemService = skuItemService;
    }

    public List<Box> calculateRecommendedBoxForDog(final QuestionnaireDTO questDto,
            final AnimalCategory animalCategory) {
        List<Box> boxes = new ArrayList<>();
        Box box = new Box();
        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = adjustPreferableFood(preferableFoodId.intValue());
        String animalAgeType = adjustAnimalAgeType(questDto.getAge());
        String adultDogSize = questDto.getAdultDogSize();
        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        if(preferableFood == null) {
            for(String brand : skuBrandsByPetCategory) {
                boxes.addAll(calculateRecommendedBoxWithMixedItems(animalCategory, adultDogSize, animalAgeType,
                        purchaseFrequency, brand));
            }
        } else {
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize, animalAgeType,
                    preferableFood);
            double severalDaysFoodAmountKilos = adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount);

            for(String brand : skuBrandsByPetCategory) {
                box.getLineItems()
                        .add(createRecommendedBox(animalCategory.getId(), animalAgeType, preferableFoodId, adultDogSize,
                                severalDaysFoodAmountKilos, brand));

                box.setBrand(brand);
                boxes.add(box);
            }
        }

        return boxes;
    }

    private List<Box> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
            final String adultDogSize, final String animalAgeType, final int purchaseFrequency, String brand) {
        List<Box> result = new ArrayList<>();
        Box box = new Box();
        box.setBrand(brand);

        for(FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize, animalAgeType,
                    preferableFood);
            double severalDaysFoodAmountKilos =
                    adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount) / 2;

            box.getLineItems().add(createRecommendedBox(animalCategory.getId(), animalAgeType, foodTypeId, adultDogSize,
                    severalDaysFoodAmountKilos, brand));
        }

        result.add(box);

        return result;
    }

    private Map<String, Object> createRecommendedBox(final Long animalCategoryId, final String animalAgeType,
            final Long preferableFoodId, final String adultDogSize, final double severalDaysFoodAmountKilos,
            final String brand) {

        double closestSKUWeight = skuItemService
                .findClosestSKUWeightForDog(brand, severalDaysFoodAmountKilos, animalAgeType, preferableFoodId,
                        adultDogSize, animalCategoryId);
        SKUItem skuItem = skuItemService
                .findRecommendedSKUItemForDog(animalCategoryId, brand, animalAgeType, preferableFoodId, adultDogSize,
                        closestSKUWeight, true);

        if(skuItem == null) {
            skuItem = skuItemService
                    .findRecommendedSKUItemForDog(animalCategoryId, brand, animalAgeType, preferableFoodId,
                            adultDogSize, closestSKUWeight, false);
        }

        return adjustRecommendedSKUWItems(severalDaysFoodAmountKilos, skuItem);
    }

    // норма еды в день для животного
    private int adjustFoodAmountForOneDay(final String animalCategory, final String adultAnimalSize,
            final String animalAgeType, final String preferableFood) {

        return Optional.ofNullable(dailyFoodAmountService
                .getDailyFoodAmountForDog(animalCategory, adultAnimalSize, animalAgeType, preferableFood))
                .orElseThrow(() -> new NotFoundException("Can't find daily food amount value"));
    }

    // тип корма (сухой, жидкий, смешанный)
    private String adjustPreferableFood(final int code) {
        return foodTypeService.getFoodTypeByCode(code);
    }
}




