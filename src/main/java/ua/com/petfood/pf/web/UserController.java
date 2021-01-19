package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/initialUser/create")
    public ResponseEntity createAnonUser() {
        User user = userService.createAnonUser();
        String email = user.getEmail();
        String token = jwtTokenProvider.createToken(email, user.getRole());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/testCallAnonOnly")
    public ResponseEntity testCallForAnon(){
        return ResponseEntity.ok().build();
    }

}
