package ua.com.petfood.pf.helper;

import static ua.com.petfood.pf.helper.constants.Constants.SEVEN_DAYS_FREQUENCY;
import static ua.com.petfood.pf.helper.constants.Constants.THIRTY_DAYS_FREQUENCY;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.model.Group;
import ua.com.petfood.pf.model.SKUItem;

@Component
public class BoxCalculatorHelper {

    public BoxCalculatorHelper(){}


    // частота покупки
    public int definePurchasingFrequency(final int purchaseFrequencyId) {
        return purchaseFrequencyId == 1 ? SEVEN_DAYS_FREQUENCY : THIRTY_DAYS_FREQUENCY;
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

    public List<SKUItem> adjustRecommendedSKUWeight(final double targetWeight, final SKUItem skuItem) {
        List<SKUItem> result = new ArrayList<>();
        result.add(skuItem);
        double currentWeight = skuItem.getPackageWeightKilos();

        while(currentWeight < targetWeight) {
            result.add(skuItem);
            currentWeight += skuItem.getPackageWeightKilos();
        }

        return result;
    }
}
