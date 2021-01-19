package ua.com.petfood.pf.service;


import ua.com.petfood.pf.model.User;

public interface UserService {

    User findByUsername(String username);

    User createAnonUser();

}
