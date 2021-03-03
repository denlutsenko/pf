package ua.com.petfood.pf.helper;

import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL;
import static ua.com.petfood.pf.helper.constants.Constants.PRICE_LIST;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.SKUItemService;

@Component
public class BoxForOtherHelper extends BoxCalculatorHelper {
    private final SKUItemService skuItemService;
    private final AnimalCategoryService animalCategoryService;
    private final AnimalService animalService;

    public BoxForOtherHelper(SKUItemService skuItemService, AnimalCategoryService animalCategoryService,
            AnimalService animalService) {
        this.skuItemService = skuItemService;
        this.animalCategoryService = animalCategoryService;
        this.animalService = animalService;
    }

    public Map<String, Object> getDefaultPriceByPetCategoryAndCreateAnimal(QuestionnaireDTO questDto, String token) {
        Map<String, Object> result = new HashMap<>();
        Long animalCategoryId = questDto.getPetCategoryId();

        if(animalCategoryId != null) {
            AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());

            result.put(ANIMAL, animalService.createAndSaveAnimal(questDto, animalCategory, token));
            result.put(PRICE_LIST, skuItemService.getSKUItemsWithPricesByAnimalCategory(animalCategory.getId()));

            return result;
        } else {
            throw new NotFoundException("animal not found");
        }
    }

}




