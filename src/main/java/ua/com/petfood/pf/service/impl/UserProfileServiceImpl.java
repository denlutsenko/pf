package ua.com.petfood.pf.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.UserProfileDTO;
import ua.com.petfood.pf.service.UserProfileService;
import ua.com.petfood.pf.service.UserService;



@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserService userService;

    public UserProfileServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserProfileDTO getUserInfoDTO(String email) {
        User user = userService.findByUsername(email).orElseThrow(() -> new NotFoundException("user email not found"));
        return mapUserToUserProfileDTO(user);
    }

    private UserProfileDTO mapUserToUserProfileDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserProfileDTO.class);
    }

}
