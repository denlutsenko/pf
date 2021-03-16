package ua.com.petfood.pf.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ua.com.petfood.pf.model.User;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User createAnonUser();

    UserDetails loadUserByUsername(String var1);
}
