package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.repository.AnimalRepository;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.UserService;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final UserHelper userHelper;
    private final UserService userService;

    @Autowired
    private AnimalServiceImpl(final AnimalRepository animalRepository, final UserHelper userHelper,
            final UserService userService) {
        this.animalRepository = animalRepository;
        this.userHelper = userHelper;
        this.userService = userService;
    }

    @Override
    public Animal createAndSaveAnimal(final QuestionnaireDTO questionnaireDto, final AnimalCategory animalCategory,
            final String bearerToken) {
        String email = userHelper.getUserEmailFromToken(bearerToken);
        User user = userService.findByUsername(email);

        Animal animal =  new Animal(animalCategory, user, questionnaireDto.getName(),
                questionnaireDto.getSubCategoryName(), questionnaireDto.getAge(), questionnaireDto.getAdultDogSize());

        return saveAnimal(animal);
    }

    private Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal); //TODO probably add error here
    }
}
