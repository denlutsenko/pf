package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.AuthenticationRequestDTO;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static ua.com.petfood.pf.helper.constants.Constants.EMAIL;
import static ua.com.petfood.pf.helper.constants.Constants.TOKEN;


@RestController
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

        @GetMapping(value = "/")
        public ResponseEntity<?> test() {
            return ResponseEntity.ok().build();
        }

    @PostMapping(value = "/api/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String username = authenticationRequestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDTO.getPassword()));
            User user = userService.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());

            Map<String, String> response = new HashMap<>();
            response.put(EMAIL, username);
            response.put(TOKEN, token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

