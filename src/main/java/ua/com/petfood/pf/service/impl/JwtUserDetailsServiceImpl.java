package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.security.jwt.JwtEmployeeFactory;
import ua.com.petfood.pf.model.Employee;
import ua.com.petfood.pf.service.EmployeeService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeService employeeService;

    @Autowired
    public JwtUserDetailsServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = employeeService.findByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException("User by email: " + email + " not found");
        }

        return JwtEmployeeFactory.create(user);
    }
}
