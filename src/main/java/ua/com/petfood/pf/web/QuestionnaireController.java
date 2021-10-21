package ua.com.petfood.pf.web;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.model.dto.response.recommendedbox.RecommendedBoxResponse;
import ua.com.petfood.pf.service.QuestionnaireService;

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
    ResponseEntity getRecommendedBoxes(@RequestBody QuestionnaireDTO questionnaireDto) {
        return ResponseEntity.ok(questionnaireService.calculateRecommendedBoxes(questionnaireDto));
    }
}
