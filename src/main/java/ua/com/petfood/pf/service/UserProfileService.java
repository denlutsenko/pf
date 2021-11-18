package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.dto.AnimalDTO;
import ua.com.petfood.pf.model.dto.UserProfileDTO;

import java.util.List;

public interface UserProfileService {
    UserProfileDTO getUserProfileDTO(String email);

    List<AnimalDTO> getUserAnimals(String email);
}
