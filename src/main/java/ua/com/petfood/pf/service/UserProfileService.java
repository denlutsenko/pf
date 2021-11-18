package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.dto.UserProfileDTO;

public interface UserProfileService {
    UserProfileDTO getUserInfoDTO(String email);
}
