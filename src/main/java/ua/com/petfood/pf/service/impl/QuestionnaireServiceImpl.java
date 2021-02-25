package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL_CATEGORIES;
import static ua.com.petfood.pf.helper.constants.Constants.CAT;
import static ua.com.petfood.pf.helper.constants.Constants.DOG;
import static ua.com.petfood.pf.helper.constants.Constants.DOG_SIZE;
import static ua.com.petfood.pf.helper.constants.Constants.FOOD_TYPES;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.BoxCalculatorForCatHelper;
import ua.com.petfood.pf.helper.BoxCalculatorForDogHelper;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.DogSizeService;
import ua.com.petfood.pf.service.FoodTypeService;
import ua.com.petfood.pf.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private AnimalCategoryService animalCategoryService;
    private AnimalService animalService;
    private FoodTypeService foodTypeService;
    private DogSizeService dogSizeService;
    private BoxCalculatorForDogHelper boxCalculatorForDogHelper;
    private BoxCalculatorForCatHelper boxCalculatorForCatHelper;

    public QuestionnaireServiceImpl(final AnimalCategoryService animalCategoryService,
            final AnimalService animalService, final FoodTypeService foodTypeService,
            final DogSizeService dogSizeService, final BoxCalculatorForDogHelper boxCalculatorForDogHelper,
            final BoxCalculatorForCatHelper boxCalculatorForCatHelper) {
        this.animalCategoryService = animalCategoryService;
        this.foodTypeService = foodTypeService;
        this.dogSizeService = dogSizeService;
        this.boxCalculatorForDogHelper = boxCalculatorForDogHelper;
        this.boxCalculatorForCatHelper = boxCalculatorForCatHelper;
        this.animalService = animalService;
    }

    @Autowired
    @Override
    public Map<String, Object> getAllCatalogsForQuestionnaire() {

        return Map.of(ANIMAL_CATEGORIES, animalCategoryService.getAnimalCategories(), FOOD_TYPES,
                foodTypeService.getFoodTypes(), DOG_SIZE, dogSizeService.getDogSizes());
    }

    @Override
    public Map<String, List<SKUItem>> calculateRecommendedBoxes(final QuestionnaireDTO questDto, final String token) {
        Map<String, List<SKUItem>> result;
        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());

        if(DOG.equalsIgnoreCase(animalCategory.getName())) {
            result = boxCalculatorForDogHelper.calculateRecommendedBoxForDog(questDto, animalCategory);
        } else if(CAT.equalsIgnoreCase(animalCategory.getName())) {
            result = boxCalculatorForCatHelper.calculateRecommendedBoxForCat(questDto, animalCategory);
        } else {
            result = null;
        }
        animalService.createAndSaveAnimal(questDto, animalCategory, token);

        return result;
    }
}
