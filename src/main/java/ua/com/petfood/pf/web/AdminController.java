package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    private AdminController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/get/registered/all")
    public ResponseEntity<?> getAllRegisteredUsers() {
        return ResponseEntity.ok(userService.getAllRegisteredUsers());
    }

    @GetMapping(value = "/users/get/anon/all")
    public ResponseEntity<?> getAllAnonUsers() {
        return ResponseEntity.ok(userService.getAllAnonUsers());
    }
}
