package com.spring.company.service;

import com.spring.company.model.Employee;
import com.spring.company.repository.EmployeeRepository;
import com.spring.company.repository.EmployeeRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepositoryCRUD employeeRepositoryCRUD;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Employee> getAllEmployees() {
        return employeeRepositoryCRUD.findAll();
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        /*
            for(int i = 0; i < employees.size(); i++) {
                if(employees.get(i).getUuid().equals(uuid)){
                    return employees.get(i);
                }
            }
         */
        return employeeRepositoryCRUD.findById(uuid).get();
    }

    public Employee addEmployee(Employee employee) {
        employee.setUuid(UUID.randomUUID());
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        return employeeRepositoryCRUD.save(employee);
    }

    public Employee updateEmployee(Employee employee, UUID uuid) {
        employee.setUuid(uuid);
        return employeeRepositoryCRUD.save(employee);
    }

    public Employee partiallyUpdateEmployee(Employee employee, UUID uuid) {
        Employee oldEmployee = employeeRepositoryCRUD.findById(uuid).get();
        if (employee.getFirstName() != null) {
            oldEmployee.setFirstName(employee.getFirstName());
        }

        if (employee.getLastName() != null) {
            oldEmployee.setLastName(employee.getLastName());
        }

        if (employee.getDateOfBirth() != null) {
            oldEmployee.setDateOfBirth(employee.getDateOfBirth());
        }

        if (employee.getRole() != null) {
            oldEmployee.setRole(employee.getRole());
        }

        if (employee.getProject() != null) {
            oldEmployee.setProject(employee.getProject());
        }
        return employeeRepositoryCRUD.save(oldEmployee);
    }

    public void deleteEmployee(UUID uuid) {
        Employee employee = employeeRepositoryCRUD.findById(uuid).get();
        employeeRepositoryCRUD.delete(employee);
    }

    public List<Employee> filterEmployees(String criteria) {
        return employeeRepositoryCRUD.findEmployeeByFirstNameContainingOrLastNameContaining(criteria, criteria);
    }

    public Employee getByEmail(String email) {
        return employeeRepositoryCRUD.findByEmail(email).orElse(null);
    }

}
