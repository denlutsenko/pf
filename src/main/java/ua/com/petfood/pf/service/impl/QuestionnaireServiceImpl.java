package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.helper.BoxHelper;
import ua.com.petfood.pf.model.AnimalCategory;
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
    private BoxHelper boxHelper;
    private SKUItemService skuItemService;

    @Autowired
    public QuestionnaireServiceImpl(
            final AnimalCategoryService animalCategoryService,
            final FoodTypeService foodTypeService,
            final DogSizeService dogSizeService,
            final BoxHelper boxHelper,
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

    public Map<String, List<SKUItem>> calculateRecommendedBoxes(final QuestionnaireDto questDto) {
        Map<String, List<SKUItem>> result = new HashMap<>();
        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());


        if (DOG.equalsIgnoreCase(animalCategory.getName())) {
            result = calculateRecommendedBoxForDog(questDto, animalCategory);
        } else if (CAT.equalsIgnoreCase(animalCategory.getName())) {
            //  result = calculateRecommendedBoxForCat();
        } else {
            // result = calculateRecommendedBoxForOther();
        }
        return result;
    }

    private Map<String, List<SKUItem>> calculateRecommendedBoxForDog(final QuestionnaireDto questDto,
                                                                     final AnimalCategory animalCategory) {
        Long preferableFoodId = questDto.getPreferableFoodId();
        String preferableFood = boxHelper.adjustPreferableFood(preferableFoodId);
        String animalAgeType = boxHelper.adjustAnimalAgeType(questDto.getAge());
        String adultDogSize = questDto.getAdultDogSize();
        int dailyFoodAmount = boxHelper.adjustFoodAmountForOneDay(animalCategory.getName(), adultDogSize,
                animalAgeType, preferableFood);
        int purchaseFrequency = boxHelper.definePurchasingFrequency(questDto.getPurchaseFrequencyId());
        double severalDaysFoodAmountKilos = boxHelper.adjustFoodAmountForSeveralDaysInKilos(purchaseFrequency,
                dailyFoodAmount);

        return createRecommendedBoxes(animalCategory.getId(), animalAgeType,
                preferableFoodId, adultDogSize, severalDaysFoodAmountKilos, purchaseFrequency);
    }

    private Map<String, List<SKUItem>> createRecommendedBoxes(final Long animalCategoryId,
                                                              final String animalAgeType,
                                                              final Long preferableFoodId,
                                                              final String adultDogSize,
                                                              final double severalDaysFoodAmountKilos,
                                                              final int purchaseFrequency) {
        Map<String, List<SKUItem>> result = new HashMap<>();
        List<String> skuBrands = skuItemService.getSkuBrandsByPetCategory(animalCategoryId);

        for (String brand : skuBrands) {
            double closestSKUWeight = skuItemService.findClosestSKUWeight(brand, severalDaysFoodAmountKilos,
                    animalAgeType, preferableFoodId, adultDogSize);

            SKUItem skuItem = skuItemService.findRecommendedSKUItemForDog(animalCategoryId, brand,
                    animalAgeType, preferableFoodId, adultDogSize, closestSKUWeight, true);

            if (skuItem == null) {
                skuItem = skuItemService.findRecommendedSKUItemForDog(animalCategoryId, brand,
                        animalAgeType, preferableFoodId, adultDogSize, closestSKUWeight, false);
            }
            result.put(String.valueOf(purchaseFrequency).concat(UNDERSCORE).concat(brand),
                    adjustRecommendedSKUWeight(severalDaysFoodAmountKilos, skuItem));
        }

        return result;
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
