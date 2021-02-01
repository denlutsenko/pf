package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Component;
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
public class BoxHelper {

    private FoodTypeService foodTypeService;
    private AnimalCategoryService animalCategoryService;
    private DailyFoodAmountService dailyFoodAmountService;

    public BoxHelper(final FoodTypeService foodTypeService,
                     final AnimalCategoryService animalCategoryService,
                     final DailyFoodAmountService dailyFoodAmountService) {
        this.foodTypeService = foodTypeService;
        this.animalCategoryService = animalCategoryService;
        this.dailyFoodAmountService = dailyFoodAmountService;
    }



    public Map<String, Object> adjustDataForDog(final String animalCategory,
                                                              final QuestionnaireDto questDto) {
        Map<String, Object> result = new HashMap<>();

        String preferableFood = defineFoodType(questDto.getPreferableFoodId());
        String animalAgeType = defineAnimalAgeType(questDto.getAge());
        int dailyFoodAmount = calculateFoodAmountForOneDay(animalCategory, questDto.getAdultDogSize(), animalAgeType,
                preferableFood);
        int purchaseFrequency = definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        double severalDaysFoodAmount = calculateFoodAmountForSeveralDaysInKilos(purchaseFrequency, dailyFoodAmount);

        result.put(PREFERABLE_FOOD, preferableFood);
        result.put(ANIMAL__AGE_TYPE, animalAgeType);
        result.put(PURCHASE_FREQUENCY, purchaseFrequency);
        result.put(SEVERAL_DAYS_FOOD_AMOUNT, severalDaysFoodAmount);


        return result;
    }


    private Map<String, List<SKUItem>> calculateRecommendedBoxForCat() {
        return null;
    }


    private Map<String, List<SKUItem>> calculateRecommendedBoxForFish() {
        return null;
    }

    private Map<String, List<SKUItem>> calculateRecommendedBoxForRodent() {
        return null;
    }


    private Map<String, List<SKUItem>> calculateRecommendedBoxForBird() {
        return null;
    }


    private Map<String, List<SKUItem>> calculateRecommendedBoxForReptile() {
        return null;
    }

    // норма еды в день для животного
    private int calculateFoodAmountForOneDay(final String animalCategory, final String adultAnimalSize,
                                             final String animalAgeType, final String preferableFood) {
        if (DOG.equalsIgnoreCase(animalCategory)) {
            return dailyFoodAmountService.getDailyFoodAmountForDog(animalCategory, adultAnimalSize, animalAgeType,
                    preferableFood);
        } else {
            return dailyFoodAmountService.getDailyFoodAmountForCat();
        }
    }

    private double calculateFoodAmountForSeveralDaysInKilos(final int days, final int dailyFoodAmount) {
        BigDecimal dayDecimal = new BigDecimal(days);
        BigDecimal dailyFoodAmountDecimal = new BigDecimal(dailyFoodAmount);

        BigDecimal divide = dayDecimal.multiply(dailyFoodAmountDecimal)
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);

        return divide.doubleValue();
    }


    // возраст животного
    private String defineAnimalAgeType(int age) {
        if (age >= 0 && age <= 12) {
            return Group.BABY.name();
        } else if (age >= 13 && age <= 72) {
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
    private String defineFoodType(final Long id) {
        return foodTypeService.getFoodTypeById(id).getType();
    }
}




