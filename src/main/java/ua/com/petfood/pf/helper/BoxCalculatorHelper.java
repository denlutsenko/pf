package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Component;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.Group;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.DailyFoodAmountService;
import ua.com.petfood.pf.service.FoodTypeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.petfood.pf.helper.constants.Constants.*;

@Component
public class BoxCalculatorHelper {

    private FoodTypeService foodTypeService;
    private DailyFoodAmountService dailyFoodAmountService;

    public BoxCalculatorHelper(final FoodTypeService foodTypeService,
                               final DailyFoodAmountService dailyFoodAmountService) {
        this.foodTypeService = foodTypeService;
        this.dailyFoodAmountService = dailyFoodAmountService;
    }

    // норма еды в день для животного
    public int adjustFoodAmountForOneDay(final String animalCategory, final String adultAnimalSize,
                                         final String animalAgeType, final String preferableFood) {

        Integer result = dailyFoodAmountService.getDailyFoodAmountForDog(animalCategory, adultAnimalSize, animalAgeType,
                preferableFood);
        if (result == null) {
            throw new NotFoundException("Can't find daily food amount value");
        }

        return result;
    }

    public double adjustFoodAmountForSeveralDaysInKilos(final int days, final int dailyFoodAmount) {
        BigDecimal dayDecimal = new BigDecimal(days);
        BigDecimal dailyFoodAmountDecimal = new BigDecimal(dailyFoodAmount);

        BigDecimal divide = dayDecimal.multiply(dailyFoodAmountDecimal)
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);

        return divide.doubleValue();
    }

    // возраст животного
    public String adjustAnimalAgeType(int age) {
        if (age >= 0 && age <= 12) {
            return Group.BABY.name();
        } else if (age >= 13 && age <= 72) {
            return Group.ADULT.name();
        } else {
            return Group.OLD.name();
        }
    }

    // частота покупки
    public int definePurchasingFrequency(final int purchaseFrequencyId) {
        return purchaseFrequencyId == 1 ? SEVEN_DAYS_FREQUENCY : THIRTY_DAYS_FREQUENCY;
    }

    // тип корма (сухой, жидкий, смешанный)
    public String adjustPreferableFood(final int code) {
        return foodTypeService.getFoodTypeByCode(code);
    }
}




