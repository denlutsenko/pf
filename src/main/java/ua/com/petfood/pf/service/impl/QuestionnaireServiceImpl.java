package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL;
import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL_CATEGORIES;
import static ua.com.petfood.pf.helper.constants.Constants.CAT;
import static ua.com.petfood.pf.helper.constants.Constants.DOG;
import static ua.com.petfood.pf.helper.constants.Constants.DOG_SIZE;
import static ua.com.petfood.pf.helper.constants.Constants.FOOD_TYPES;
import static ua.com.petfood.pf.helper.constants.Constants.RECOMMENDED_BOXES;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.BoxCalculatorForCatHelper;
import ua.com.petfood.pf.helper.BoxCalculatorForDogHelper;
import ua.com.petfood.pf.helper.BoxForOtherHelper;
import ua.com.petfood.pf.model.AnimalCategory;
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
    private BoxForOtherHelper boxCalculatorForOtherHelper;

    public QuestionnaireServiceImpl(final AnimalCategoryService animalCategoryService,
            final AnimalService animalService, final FoodTypeService foodTypeService,
            final DogSizeService dogSizeService, final BoxCalculatorForDogHelper boxCalculatorForDogHelper,
            final BoxCalculatorForCatHelper boxCalculatorForCatHelper,
            final BoxForOtherHelper boxCalculatorForOtherHelper) {
        this.animalCategoryService = animalCategoryService;
        this.foodTypeService = foodTypeService;
        this.dogSizeService = dogSizeService;
        this.boxCalculatorForDogHelper = boxCalculatorForDogHelper;
        this.boxCalculatorForCatHelper = boxCalculatorForCatHelper;
        this.animalService = animalService;
        this.boxCalculatorForOtherHelper = boxCalculatorForOtherHelper;
    }

    @Autowired
    @Override
    public Map<String, Object> getAllCatalogsForQuestionnaire() {

        return Map.of(ANIMAL_CATEGORIES, animalCategoryService.getAnimalCategories(), FOOD_TYPES,
                foodTypeService.getFoodTypes(), DOG_SIZE, dogSizeService.getDogSizes());
    }

    @Override
    public Map<String, Object> calculateRecommendedBoxes(final QuestionnaireDTO questDto, final String token) {
        Map<String, Object> result = new HashMap<>();
        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());

        if(DOG.equalsIgnoreCase(animalCategory.getName())) {
            result.put(RECOMMENDED_BOXES,
                    boxCalculatorForDogHelper.calculateRecommendedBoxForDog(questDto, animalCategory));
        }

        if(CAT.equalsIgnoreCase(animalCategory.getName())) {
            result.put(RECOMMENDED_BOXES,
                    boxCalculatorForCatHelper.calculateRecommendedBoxForCat(questDto, animalCategory));
        }

        if(!result.isEmpty()) {
            result.put(ANIMAL, animalService.createAndSaveAnimal(questDto, animalCategory, token));
        }

        return result;
    }
}
