package ua.com.petfood.pf.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class AuthenticationRestControllerV1 {

    @GetMapping
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }
}

