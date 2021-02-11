package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.security.jwt.JwtEmployeeFactory;
import ua.com.petfood.pf.service.UserService;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByUsername(email);

        return JwtEmployeeFactory.create(user);
    }
}
