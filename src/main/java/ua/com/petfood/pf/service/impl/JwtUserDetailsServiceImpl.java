package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.security.jwt.JwtEmployeeFactory;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByUsername(email);

        return JwtEmployeeFactory.create(user);
    }
}
