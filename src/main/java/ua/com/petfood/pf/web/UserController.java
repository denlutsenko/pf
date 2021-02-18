package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin
public class UserController {
    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/api/initialUser/create")
    public ResponseEntity createAnonUser() {
        User user = userService.createAnonUser();
        String email = user.getEmail();
        String token = jwtTokenProvider.createToken(email, user.getRole());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/anon/testCallAnonOnly")
    public ResponseEntity testCallForAnon() {
        return ResponseEntity.ok().build();
    }

}
