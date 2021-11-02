package ua.com.petfood.pf.web;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.service.AnimalService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(final AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(value = "/animal/create")
    ResponseEntity<?> getRecommendedBoxes(@RequestHeader(AUTHORIZATION) String token,
            @RequestBody QuestionnaireDTO questionnaireDto) {
        return ResponseEntity.ok(animalService.createAndSaveAnimal(questionnaireDto, token));
    }
}
