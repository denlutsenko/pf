package ua.com.petfood.pf.web;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.helper.BoxForOtherHelper;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.service.SKUItemService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CatalogController {

    private final SKUItemService skuItemService;
    private final BoxForOtherHelper boxForOtherHelper;

    @Autowired
    public CatalogController(SKUItemService skuItemService, BoxForOtherHelper boxForOtherHelper) {
        this.skuItemService = skuItemService;
        this.boxForOtherHelper = boxForOtherHelper;
    }

    @GetMapping(value = "/items/get/{id}")
    ResponseEntity getLineItemsForAnimalCategoryById(@PathVariable(name = "id") Long animalCategoryId) {
        return ResponseEntity.ok(skuItemService.getSKUItemsByAnimalCategoryID(animalCategoryId));
    }

    @GetMapping(value = "/catalog/get/{id}")
    ResponseEntity getItemsWithPriceByAnimalCategory(@PathVariable(name = "id") Long animalCategoryId) {
        return ResponseEntity.ok(skuItemService.getSKUItemsWithPricesByAnimalCategory(animalCategoryId));
    }

    @PostMapping(value = "/catalog")
    ResponseEntity getItemsWithPriceByAnimalCategory(@RequestBody QuestionnaireDTO questionnaireDto,
            @RequestHeader(AUTHORIZATION) String token) {

        return ResponseEntity
                .ok(boxForOtherHelper.getDefaultPriceByPetCategoryAndCreateAnimal(questionnaireDto, token));
    }
}
