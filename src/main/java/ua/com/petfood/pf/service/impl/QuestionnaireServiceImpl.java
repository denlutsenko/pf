package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL_CATEGORIES;
import static ua.com.petfood.pf.helper.constants.Constants.CAT;
import static ua.com.petfood.pf.helper.constants.Constants.DOG;
import static ua.com.petfood.pf.helper.constants.Constants.DOG_SIZE;
import static ua.com.petfood.pf.helper.constants.Constants.FOOD_TYPES;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.BoxCalculatorForCatHelper;
import ua.com.petfood.pf.helper.BoxCalculatorForDogHelper;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.model.dto.response.recommendedbox.Box;
import ua.com.petfood.pf.model.dto.response.recommendedbox.RecommendedBoxResponse;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.DogSizeService;
import ua.com.petfood.pf.service.FoodTypeService;
import ua.com.petfood.pf.service.QuestionnaireService;
import ua.com.petfood.pf.service.SKUPriceService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private AnimalCategoryService animalCategoryService;
    private FoodTypeService foodTypeService;
    private DogSizeService dogSizeService;
    private BoxCalculatorForDogHelper boxCalculatorForDogHelper;
    private BoxCalculatorForCatHelper boxCalculatorForCatHelper;
    private SKUPriceService skuPriceService;

    public QuestionnaireServiceImpl(final AnimalCategoryService animalCategoryService,
            final FoodTypeService foodTypeService, final DogSizeService dogSizeService,
            final BoxCalculatorForDogHelper boxCalculatorForDogHelper,
            final BoxCalculatorForCatHelper boxCalculatorForCatHelper, final SKUPriceService skuPriceService) {
        this.skuPriceService = skuPriceService;
        this.animalCategoryService = animalCategoryService;
        this.foodTypeService = foodTypeService;
        this.dogSizeService = dogSizeService;
        this.boxCalculatorForDogHelper = boxCalculatorForDogHelper;
        this.boxCalculatorForCatHelper = boxCalculatorForCatHelper;
    }

    @Autowired
    @Override
    public Map<String, Object> getAllCatalogsForQuestionnaire() {

        return Map.of(ANIMAL_CATEGORIES, animalCategoryService.getAnimalCategories(), FOOD_TYPES,
                foodTypeService.getFoodTypes(), DOG_SIZE, dogSizeService.getDogSizes());
    }

    @Override
    public RecommendedBoxResponse calculateRecommendedBoxes(final QuestionnaireDTO questDto) {
        RecommendedBoxResponse response = new RecommendedBoxResponse();

        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());

        if(DOG.equalsIgnoreCase(animalCategory.getName())) {
            response.setBoxes(boxCalculatorForDogHelper.calculateRecommendedBoxForDog(questDto, animalCategory));
        }

        if(CAT.equalsIgnoreCase(animalCategory.getName())) {
            response.setBoxes(boxCalculatorForCatHelper.calculateRecommendedBoxForCat(questDto, animalCategory));
        }

        for(var box : response.getBoxes()) {
                calculateTotalPrice(box);
        }

        return response;
    }

    private void calculateTotalPrice(final Box recommendedBoxItems) {
        BigDecimal totalPriceItems = new BigDecimal("0.00");

        for(Map skuItem : recommendedBoxItems.getLineItems()) {
            SKUItem lineItem = (SKUItem)skuItem.get("lineItem");
            Integer lineItemCount = (Integer)skuItem.get("count");
            BigDecimal sellingPrice = skuPriceService.getPriceForSingleItem(lineItem.getId());
            totalPriceItems = totalPriceItems.add(sellingPrice.multiply(BigDecimal.valueOf(lineItemCount)))
                    .setScale(2, RoundingMode.HALF_DOWN);
        }

        recommendedBoxItems.setTotalPrice(totalPriceItems);
    }
}
