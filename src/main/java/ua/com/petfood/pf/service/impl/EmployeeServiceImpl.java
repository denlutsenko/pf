package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.Employee;
import ua.com.petfood.pf.model.Role;
import ua.com.petfood.pf.model.RoleName;
import ua.com.petfood.pf.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public EmployeeServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee findByUsername(String email) {
        Employee employee = new Employee();

        Role role = new Role();
        role.setName(RoleName.ANONYMOUS);

        employee.setEmail("a");
        employee.setPassword("a");
        employee.setRole(role);

        return employee;
    }
}
