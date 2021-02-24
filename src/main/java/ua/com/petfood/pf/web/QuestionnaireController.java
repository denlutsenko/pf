package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;
import ua.com.petfood.pf.service.QuestionnaireService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping(value = "/questions/catalogs/all")
    ResponseEntity getCatalogsForQuestionnaire() {
        return ResponseEntity.ok(questionnaireService.getAllCatalogsForQuestionnaire());
    }

    @PostMapping(value = "/meal/recommend")
    ResponseEntity getRecommendedBoxes(@RequestBody QuestionnaireDto questionnaireDto) {
        return ResponseEntity.ok(questionnaireService.calculateRecommendedBoxes(questionnaireDto));
    }
}
