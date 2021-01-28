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

    //    temporary stub. Logic will be implemented in another task (task PF-38)
    @PostMapping(value = "/meal/recommend")
    ResponseEntity getRecommendedBoxes(@RequestBody QuestionnaireDto questionnaireDto) {
        SKUItem item1 = new SKUItem();
        item1.setId(1L);
        item1.setTitle("title 1");
        item1.setBrand("Brand 1");
        item1.setImage("IMG PATH 1");

        SKUItem item2 = new SKUItem();
        item2.setId(2L);
        item2.setTitle("title 2");
        item2.setBrand("Brand 2");
        item2.setImage("IMG PATH 2");

        SKUItem item3 = new SKUItem();
        item3.setId(3L);
        item3.setTitle("title 3");
        item3.setBrand("Brand 3");
        item3.setImage("IMG PATH 3");

        SKUItem item4 = new SKUItem();
        item4.setId(4L);
        item4.setTitle("title 4");
        item4.setBrand("Brand 4");
        item4.setImage("IMG PATH 4");

        return ResponseEntity.ok(List.of(item1, item2, item3, item4, questionnaireDto));
    }
}
