package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.helper.BoxCalculatorHelper;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.FoodType;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.petfood.pf.helper.constants.Constants.*;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private AnimalCategoryService animalCategoryService;
    private FoodTypeService foodTypeService;
    private DogSizeService dogSizeService;
    private BoxCalculatorHelper boxHelper;
    private SKUItemService skuItemService;

    @Autowired
    public QuestionnaireServiceImpl(
            final AnimalCategoryService animalCategoryService,
            final FoodTypeService foodTypeService,
            final DogSizeService dogSizeService,
            final BoxCalculatorHelper boxHelper,
            final SKUItemService skuItemService) {
        this.animalCategoryService = animalCategoryService;
        this.foodTypeService = foodTypeService;
        this.dogSizeService = dogSizeService;
        this.boxHelper = boxHelper;
        this.skuItemService = skuItemService;
    }

    @Override
    public Map<String, Object> getAllCatalogsForQuestionnaire() {

        return Map.of(
                "animalCategories", animalCategoryService.getAnimalCategories(),
                "foodTypes", foodTypeService.getFoodTypes(),
                "dogSize", dogSizeService.getDogSizes());
    }

    @Override
    public Map<String, List<SKUItem>> calculateRecommendedBoxes(final QuestionnaireDto questDto) {
        Map<String, List<SKUItem>> result = new HashMap<>();
        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());


        if (DOG.equalsIgnoreCase(animalCategory.getName())) {
            result = calculateRecommendedBoxForDog(questDto, animalCategory);
        } else if (CAT.equalsIgnoreCase(animalCategory.getName())) {
//              result = calculateRecommendedBoxForCat();
        } else {
            // result = calculateRecommendedBoxForOther();
        }
        return result;
    }

    private Map<String, List<SKUItem>> calculateRecommendedBoxForDog(final QuestionnaireDto questDto,
                                                                     final AnimalCategory animalCategory) {
        Map<String, List<SKUItem>> result = new HashMap<>();

        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = boxHelper.adjustPreferableFood(preferableFoodId.intValue());
        String animalAgeType = boxHelper.adjustAnimalAgeType(questDto.getAge());
        String adultDogSize = questDto.getAdultDogSize();
        int purchaseFrequency = boxHelper.definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        List<String> skuBrandsByPetCategory = skuItemService.getSkuBrandsByPetCategory(animalCategory.getId());

        if (preferableFood == null) {
            for (String brand : skuBrandsByPetCategory) {
                result.put(brand, calculateRecommendedBoxWithMixedItems(animalCategory, adultDogSize, animalAgeType,
                        purchaseFrequency, brand));
            }
        } else {
            int dailyFoodAmount = boxHelper.adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize,
                    animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos = boxHelper.adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount);

            for (String brand : skuBrandsByPetCategory) {
                result.put(brand, createRecommendedBox(animalCategory.getId(), animalAgeType,
                        preferableFoodId, adultDogSize, severalDaysFoodAmountKilos, brand));
            }
        }

        return result;
    }

    private List<SKUItem> calculateRecommendedBoxWithMixedItems(final AnimalCategory animalCategory,
                                                                final String adultDogSize, final String animalAgeType,
                                                                final int purchaseFrequency, String brand) {
        List<SKUItem> result = new ArrayList<>();
        for (FoodType foodType : foodTypeService.getFoodTypes()) {
            Long foodTypeId = foodType.getId() != null ? foodType.getId() : 1L;

            String preferableFood = boxHelper.adjustPreferableFood(foodTypeId.intValue());
            int dailyFoodAmount = boxHelper.adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize,
                    animalAgeType, preferableFood);
            double severalDaysFoodAmountKilos = boxHelper.adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                    dailyFoodAmount) / 2;
            List<SKUItem> recommendedMixedBox = createRecommendedBox(animalCategory.getId(), animalAgeType, foodTypeId,
                    adultDogSize, severalDaysFoodAmountKilos, brand);

            result.addAll(recommendedMixedBox);
        }
        return result;
    }


    private List<SKUItem> createRecommendedBox(final Long animalCategoryId,
                                               final String animalAgeType,
                                               final Long preferableFoodId,
                                               final String adultDogSize,
                                               final double severalDaysFoodAmountKilos,
                                               String brand) {

        double closestSKUWeight = skuItemService.findClosestSKUWeight(brand, severalDaysFoodAmountKilos,
                animalAgeType, preferableFoodId, adultDogSize);

        SKUItem skuItem = skuItemService.findRecommendedSKUItemForDog(animalCategoryId, brand,
                animalAgeType, preferableFoodId, adultDogSize, closestSKUWeight, true);

        if (skuItem == null) {
            skuItem = skuItemService.findRecommendedSKUItemForDog(animalCategoryId, brand,
                    animalAgeType, preferableFoodId, adultDogSize, closestSKUWeight, false);
        }

        return adjustRecommendedSKUWeight(severalDaysFoodAmountKilos, skuItem);
    }

    private List<SKUItem> adjustRecommendedSKUWeight(final double targetWeight, final SKUItem skuItem) {
        List<SKUItem> result = new ArrayList<>();
        result.add(skuItem);
        double currentWeight = skuItem.getPackageWeightKilos();

        while (currentWeight < targetWeight) {
            result.add(skuItem);
            currentWeight += skuItem.getPackageWeightKilos();
        }

        return result;
    }
}
