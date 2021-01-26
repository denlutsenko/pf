package ua.com.petfood.pf.service.impl;

import org.springframework.stereotype.Service;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.DogSizeService;
import ua.com.petfood.pf.service.FoodTypeService;
import ua.com.petfood.pf.service.QuestionnaireService;

import java.util.Map;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private AnimalCategoryService animalCategoryService;
    private FoodTypeService foodTypeService;
    private DogSizeService dogSizeService;

    public QuestionnaireServiceImpl(
            AnimalCategoryService animalCategoryService,
            FoodTypeService foodTypeService,
            DogSizeService dogSizeService) {
        this.animalCategoryService = animalCategoryService;
        this.foodTypeService = foodTypeService;
        this.dogSizeService = dogSizeService;
    }

    @Override
    public Map<String, Object> getAllCatalogsForQuestionnaire() {

        return Map.of(
                "animalCategories", animalCategoryService.getAnimalCategories(),
                "foodTypes", foodTypeService.getFoodTypes(),
                "dogSize", dogSizeService.getDogSizes());
    }
}
