package ua.com.petfood.pf.helper;

import static ua.com.petfood.pf.helper.constants.Constants.SEVEN_DAYS_FREQUENCY;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.SKUItemService;

@Component
public class BoxCalculatorForOthersHelper extends BoxCalculatorHelper {

    private SKUItemService skuItemService;

    @Autowired
    private BoxCalculatorForOthersHelper(final SKUItemService skuItemService) {
        this.skuItemService = skuItemService;
    }

    public Map<String, List<SKUItem>> calculateRecommendedBoxForOther(final QuestionnaireDto questDto,
            final AnimalCategory animalCategory) {
        Map<String, List<SKUItem>> result = new HashMap<>();
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        int foodAmount = questDto.getRecommendedMealAmount();
        double severalDaysFoodAmountKilos = adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency, foodAmount);

        for(String brand : skuBrandsByPetCategory) {
            result.put(brand, createRecommendedBox(animalCategory.getId(), severalDaysFoodAmountKilos, brand));
        }

        return result;
    }

    private List<SKUItem> createRecommendedBox(final Long animalCategoryId, final double severalDaysFoodAmountKilos,
            final String brand) {

        double closestSKUWeight = skuItemService
                .findClosestSKUWeightForOthers(animalCategoryId, severalDaysFoodAmountKilos, brand);

        SKUItem skuItem = skuItemService
                .findRecommendedSKUItemForOthers(animalCategoryId, brand, closestSKUWeight, true);

        if(skuItem == null) {
            skuItem = skuItemService.findRecommendedSKUItemForOthers(animalCategoryId, brand, closestSKUWeight, false);
        }

        return adjustRecommendedSKUWeight(severalDaysFoodAmountKilos, skuItem);

    }

    public double adjustFoodAmountForSeveralDaysInKilos(final int days, final int dailyFoodAmount) {
        BigDecimal numberOfWeeks = defineNumberOfWeeks(days);
        BigDecimal foodAmount = new BigDecimal(dailyFoodAmount);

        return numberOfWeeks.multiply(foodAmount).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private BigDecimal defineNumberOfWeeks(final int days) {
        return days == SEVEN_DAYS_FREQUENCY ? BigDecimal.valueOf(1) : BigDecimal.valueOf(4);
    }

}
