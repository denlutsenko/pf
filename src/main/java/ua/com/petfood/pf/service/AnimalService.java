package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;

public interface AnimalService {

    Animal createAndSaveAnimal(QuestionnaireDto questionnaireDto, AnimalCategory animalCategory, String token);
}
