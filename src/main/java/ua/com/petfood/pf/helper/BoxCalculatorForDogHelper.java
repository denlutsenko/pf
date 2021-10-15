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

import java.util.ArrayList;
import java.util.HashMap;
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

    public Map<String, List<Map>> calculateRecommendedBoxForDog(final QuestionnaireDTO questDto,
            final AnimalCategory animalCategory) {
        Map<String, List<Map>> result = new HashMap<>();

        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = adjustPreferableFood(preferableFoodId.intValue());
        String animalAgeType = adjustAnimalAgeType(questDto.getAge());
        String adultDogSize = questDto.getAdultDogSize();
        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        if(preferableFood == null) {
            for(String brand : skuBrandsByPetCategory) {
                result.put(brand, calculateRecommendedBoxWithMixedItems(animalCategory, adultDogSize, animalAgeType,
                        purchaseFrequency, brand));
            }
        } else {
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize, animalAgeType,
                    preferableFood);
            double severalDaysFoodAmountKilos = adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount);

            for(String brand : skuBrandsByPetCategory) {
                List<Map> recommendedBox = List
                        .of(createRecommendedBox(animalCategory.getId(), animalAgeType, preferableFoodId, adultDogSize,
                                severalDaysFoodAmountKilos, brand));

                result.put(brand,recommendedBox);
            }
        }

        return result;
    }

    private List<Map> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
            final String adultDogSize, final String animalAgeType, final int purchaseFrequency, String brand) {
        List<Map> result = new ArrayList<>();

        for(FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize, animalAgeType,
                    preferableFood);
            double severalDaysFoodAmountKilos =
                    adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount) / 2;
            Map<String, Object> recommendedMixedBox = createRecommendedBox(animalCategory.getId(), animalAgeType, foodTypeId,
                    adultDogSize, severalDaysFoodAmountKilos, brand);

            result.add(recommendedMixedBox);
        }

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

        return adjustRecommendedSKUWeight(severalDaysFoodAmountKilos, skuItem);
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




