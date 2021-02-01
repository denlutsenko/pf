package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.helper.BoxHelper;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.*;

import javax.swing.*;
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

    public Map<String, List<SKUItem>> calculateRecommendedBoxes(QuestionnaireDto questDto) {
        Map<String, List<SKUItem>> result;
        String animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId()).getName();


        if (DOG.equalsIgnoreCase(animalCategory)) {
            Map<String, Object> data = boxHelper.adjustDataForDog(animalCategory, questDto);
            result = calculateRecommendedBoxForDog(animalCategory, questDto);
        } else if (CAT.equalsIgnoreCase(animalCategory)) {
            result = calculateRecommendedBoxForCat();
        } else if (FISH.equalsIgnoreCase(animalCategory)) {
            result = calculateRecommendedBoxForFish();
        } else if (RODENT.equalsIgnoreCase(animalCategory)) {
            result = calculateRecommendedBoxForRodent();
        } else if (BIRD.equalsIgnoreCase(animalCategory)) {
            result = calculateRecommendedBoxForBird();
        } else {
            result = calculateRecommendedBoxForReptile();
        }
        return result;
    }

    private void calculateRecommendedBoxForDog(){
        skuItemService.findRecommendedSKUItemForDog();
    }
}
