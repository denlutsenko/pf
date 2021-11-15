package ua.com.petfood.pf.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.UserDTO;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);

    User createAnonUser();

    UserDetails loadUserByUsername(String var1);

    User createUser(UserDTO userDTO);

    User updateUserFromDeliveryAddress(User userAnon, DeliveryAddress deliveryAddress);

    Authentication getAuthentication(String email);

    User createUserPassword(String token, UserDTO userId);
}
