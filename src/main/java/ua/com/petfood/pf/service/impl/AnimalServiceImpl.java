package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.AnimalCategory;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.repository.AnimalRepository;
import ua.com.petfood.pf.service.AnimalCategoryService;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.UserService;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalCategoryService animalCategoryService;
    private final UserHelper userHelper;
    private final UserService userService;

    @Autowired
    private AnimalServiceImpl(final AnimalRepository animalRepository, final UserHelper userHelper,
            final AnimalCategoryService animalCategoryService, final UserService userService) {
        this.animalRepository = animalRepository;
        this.animalCategoryService = animalCategoryService;
        this.userHelper = userHelper;
        this.userService = userService;
    }

    @Override
    public Animal createAndSaveAnimal(final QuestionnaireDTO questionnaireDto, final AnimalCategory animalCategory,
            final String bearerToken) {
        String email = userHelper.getUserEmailFromToken(bearerToken);
        User user = userService.findByUsername(email);

        Animal animal = new Animal(animalCategory, user, questionnaireDto.getName(),
                questionnaireDto.getSubCategoryName(), questionnaireDto.getAge(), questionnaireDto.getAdultDogSize());

        return saveAnimal(animal);
    }

    @Override
    public Animal createAndSaveAnimal(final QuestionnaireDTO questDto, final String bearerToken) {
        AnimalCategory animalCategory = animalCategoryService.getAnimalCategoryById(questDto.getPetCategoryId());

        return createAndSaveAnimal(questDto, animalCategory, bearerToken);
    }

    private Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal); //TODO probably add error here
    }
}
