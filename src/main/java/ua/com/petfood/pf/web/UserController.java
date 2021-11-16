package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.UserDTO;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.UserService;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.petfood.pf.helper.constants.Constants.*;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/api/initialUser/create")
    public ResponseEntity<?> createAnonUser() {
        User user = userService.createAnonUser();
        String email = user.getEmail();
        String token = jwtTokenProvider.createToken(email, user.getRole());

        Map<Object, Object> response = new HashMap<>();
        response.put(EMAIL, email);
        response.put(TOKEN, token);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/anon/testCallAnonOnly")
    public ResponseEntity<?> testCallForAnon() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/api/registration")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);

        Map<Object, Object> response = new HashMap<>();
        response.put(EMAIL, user.getEmail());
        response.put(CREATED, user.getCreated());
        response.put(STATUS, user.getUserStatus());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/anon/password/create")
    public ResponseEntity<?> createUserPassword(@RequestHeader(AUTHORIZATION) String token,
            @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUserPassword(token, userDTO));
    }

    @PostMapping(value = "/admin/create/new")
    public ResponseEntity<?> createAdminUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createNewAdmin(userDTO));
    }
}
