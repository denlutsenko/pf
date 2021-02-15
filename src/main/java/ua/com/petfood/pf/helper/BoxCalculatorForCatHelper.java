package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.FoodType;
import ua.com.petfood.pf.model.Group;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.DailyFoodAmountService;
import ua.com.petfood.pf.service.FoodTypeService;
import ua.com.petfood.pf.service.SKUItemService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static ua.com.petfood.pf.helper.constants.Constants.SEVEN_DAYS_FREQUENCY;
import static ua.com.petfood.pf.helper.constants.Constants.THIRTY_DAYS_FREQUENCY;

@Component
public class BoxCalculatorForCatHelper {

    private FoodTypeService foodTypeService;
    private DailyFoodAmountService dailyFoodAmountService;
    private SKUItemService skuItemService;

    public BoxCalculatorForCatHelper(FoodTypeService foodTypeService, DailyFoodAmountService dailyFoodAmountService,
            SKUItemService skuItemService) {
        this.foodTypeService = foodTypeService;
        this.dailyFoodAmountService = dailyFoodAmountService;
        this.skuItemService = skuItemService;
    }

    public Map<String, List<SKUItem>> calculateRecommendedBoxForCat(final QuestionnaireDto questDto,
            final AnimalCategory animalCategory) {
        Map<String, List<SKUItem>> result = new HashMap<>();

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
                result.put(brand, createRecommendedBoxForCat(animalCategory.getId(), animalAgeType, preferableFoodId,
                        severalDaysFoodAmountKilos, brand));
            }
        }

        return result;
    }

    private List<SKUItem> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
            final String animalAgeType, final int purchaseFrequency, String brand) {
        List<SKUItem> result = new ArrayList<>();
        for(FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = adjustFoodAmountForOneDay(animalCategory.getName(), animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos =
                    adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount) / 2;
            List<SKUItem> recommendedMixedBox = createRecommendedBoxForCat(animalCategory.getId(), animalAgeType,
                    foodTypeId, severalDaysFoodAmountKilos, brand);

            result.addAll(recommendedMixedBox);
        }
        return result;
    }

    private List<SKUItem> createRecommendedBoxForCat(final Long animalCategoryId, final String animalAgeType,
            final Long preferableFoodId, final double severalDaysFoodAmountKilos, String brand) {

        double closestSKUWeight = skuItemService
                .findClosestSKUWeightForCat(brand, severalDaysFoodAmountKilos, animalAgeType, preferableFoodId);

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

    private List<SKUItem> adjustRecommendedSKUWeight(final double targetWeight, final SKUItem skuItem) {
        List<SKUItem> result = new ArrayList<>();
        result.add(skuItem);
        double currentWeight = skuItem.getPackageWeightKilos();

        while(currentWeight < targetWeight) {
            result.add(skuItem);
            currentWeight += skuItem.getPackageWeightKilos();
        }

        return result;
    }

    // норма еды в день для животного
    private int adjustFoodAmountForOneDay(final String animalCategory, final String animalAgeType,
            final String preferableFood) {

        return Optional.ofNullable(
                dailyFoodAmountService.getDailyFoodAmountForCat(animalCategory, animalAgeType, preferableFood))
                .orElseThrow(() -> new NotFoundException("Can't find daily food amount value"));
    }

    private double adjustFoodAmountForSeveralDaysInKilos(final int days, final int dailyFoodAmount) {
        BigDecimal dayDecimal = new BigDecimal(days);
        BigDecimal dailyFoodAmountDecimal = new BigDecimal(dailyFoodAmount);

        BigDecimal divide = dayDecimal.multiply(dailyFoodAmountDecimal)
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);

        return divide.doubleValue();
    }

    // возраст животного
    private String adjustAnimalAgeType(int age) {
        if(age >= 0 && age <= 12) {
            return Group.BABY.name();
        } else if(age >= 13 && age <= 72) {
            return Group.ADULT.name();
        } else {
            return Group.OLD.name();
        }
    }

    // частота покупки
    private int definePurchasingFrequency(final int purchaseFrequencyId) {
        return purchaseFrequencyId == 1 ? SEVEN_DAYS_FREQUENCY : THIRTY_DAYS_FREQUENCY;
    }

    // тип корма (сухой, жидкий, смешанный)
    private String adjustPreferableFood(final int code) {
        return foodTypeService.getFoodTypeByCode(code);
    }
}




