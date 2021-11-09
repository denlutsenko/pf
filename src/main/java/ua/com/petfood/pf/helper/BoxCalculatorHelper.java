package ua.com.petfood.pf.helper;

import static ua.com.petfood.pf.helper.constants.Constants.THIRTY_DAYS_FREQUENCY;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.model.Group;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.response.recommendedbox.Box;

@Component
public class BoxCalculatorHelper {

    public BoxCalculatorHelper() {
    }

    // частота покупки
    public int definePurchasingFrequency(final int purchaseFrequencyId) {
        //        return purchaseFrequencyId == 1 ? SEVEN_DAYS_FREQUENCY : THIRTY_DAYS_FREQUENCY;
        return THIRTY_DAYS_FREQUENCY;
    }

    // возраст животного
    public String adjustAnimalAgeType(int age) {
        if(age >= 0 && age <= 12) {
            return Group.BABY.name();
        } else if(age >= 13 && age <= 72) {
            return Group.ADULT.name();
        } else {
            return Group.OLD.name();
        }
    }

    public double adjustFoodAmountForSeveralDaysInKilos(final int days, final int dailyFoodAmount) {
        BigDecimal dayDecimal = new BigDecimal(days);
        BigDecimal dailyFoodAmountDecimal = new BigDecimal(dailyFoodAmount);

        BigDecimal divide = dayDecimal.multiply(dailyFoodAmountDecimal)
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);

        return divide.doubleValue();
    }

    public Map<String, Object> adjustRecommendedSKUWItems(final double targetWeight, final SKUItem skuItem) {
        Map<String, Object> lineItems = new HashMap<>();

        if(skuItem == null) {
            return lineItems;
        }

        int lineItemCount = 1;

        double currentWeight = skuItem.getPackageWeightKilos();

        while(currentWeight < targetWeight) {
            lineItemCount++;
            currentWeight += skuItem.getPackageWeightKilos();
        }

        lineItems.put("count", lineItemCount);
        lineItems.put("lineItem", skuItem);

        return lineItems;
    }

    protected boolean isLineItemsPresent(final Box box){
        return !box.getLineItems().isEmpty();
    }
}
