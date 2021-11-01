package ua.com.petfood.pf.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.UserDTO;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User createAnonUser();

    UserDetails loadUserByUsername(String var1);

    User createUser(UserDTO userDTO);

    Authentication getAuthentication(String email);
}
