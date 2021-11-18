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


    @GetMapping(value = "/api/get_user_profile/{email}")
    public ResponseEntity<?> getUserInfo(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable String email) {
        return ResponseEntity.ok(userProfileService.getUserInfoDTO(email));
    }
}





