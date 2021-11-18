package ua.com.petfood.pf.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.AnimalDTO;
import ua.com.petfood.pf.model.dto.UserProfileDTO;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.UserProfileService;
import ua.com.petfood.pf.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserService userService;
    private final AnimalService animalService;

    public UserProfileServiceImpl(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @Override
    public UserProfileDTO getUserProfileDTO(String email) {
        User user = userService.findByUsername(email).orElseThrow(() -> new NotFoundException("user email not found"));
        return mapUserToUserProfileDTO(user);
    }

    private UserProfileDTO mapUserToUserProfileDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserProfileDTO.class);
    }

    @Override
    public List<AnimalDTO> getUserAnimals(String email) {
        User user = userService.findByUsername(email).orElseThrow(() -> new NotFoundException("user email not found"));
        List<Animal> animalList = animalService.findAnimalsByUser(user);

        return animalList.stream()
                .map(this::mapAnimalToAnimalDTO)
                .collect(Collectors.toList());
    }

    private AnimalDTO mapAnimalToAnimalDTO(Animal animal) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(animal, AnimalDTO.class);
    }
}
