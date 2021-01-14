package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.Role;
import ua.com.petfood.pf.model.RoleName;
import ua.com.petfood.pf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String email) {
        User user = new User();

        Role role = new Role();
        role.setName(RoleName.ANONYMOUS);

        user.setEmail("a");
        user.setPassword("a");
        user.setRole(role);

        return user;
    }
}
