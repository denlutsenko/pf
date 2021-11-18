package ua.com.petfood.pf.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.service.UserProfileService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@CrossOrigin
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    @GetMapping(value = "/user/get_user_profile/{email}")
    public ResponseEntity<?> getUserProfile(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable String email) {
        return ResponseEntity.ok(userProfileService.getUserProfileDTO(email));
    }
    @GetMapping(value = "/user/get_user_animals/{email}")
    public ResponseEntity<?> getUserAnimals(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable String email) {
        return ResponseEntity.ok(userProfileService.getUserAnimals(email));
    }
}





