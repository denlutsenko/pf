package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.QuestionnaireService;
import ua.com.petfood.pf.service.SKUItemService;

@RestController
//@CrossOrigin
@RequestMapping(value = "/anon")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;
    private SKUItemService skuItemService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService, SKUItemService skuItemService) {
        this.questionnaireService = questionnaireService;
        this.skuItemService = skuItemService;
    }

    @GetMapping(value = "/questions/catalogs/all")
    ResponseEntity getCatalogsForQuestionnaire() {
        return ResponseEntity.ok(questionnaireService.getAllCatalogsForQuestionnaire());
    }

    @GetMapping(value = "/items/get/{id}")
    ResponseEntity getLineItemsForAnimalCategoryById(@PathVariable(name = "id") Long animalCategoryId) {
        return ResponseEntity.ok(skuItemService.getSKUItemsByAnimalCategoryID(animalCategoryId));
    }

    @PostMapping(value = "/meal/recommend")
    ResponseEntity getRecommendedBoxes(@RequestBody QuestionnaireDto questionnaireDto) {
        return ResponseEntity.ok(questionnaireService.calculateRecommendedBoxes(questionnaireDto));
    }
}
