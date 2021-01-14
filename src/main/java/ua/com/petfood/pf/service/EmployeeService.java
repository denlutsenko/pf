package ua.com.petfood.pf.service;


import ua.com.petfood.pf.model.Employee;

public interface EmployeeService {

    Employee findByUsername(String username);

}
