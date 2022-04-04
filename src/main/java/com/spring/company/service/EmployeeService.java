package com.spring.company.service;

import com.spring.company.model.Employee;
import com.spring.company.model.EmployeeRole;
import com.spring.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(UUID.randomUUID(), "Ion", "Popescu", null, EmployeeRole.MEMBER));
        employees.add(new Employee(UUID.randomUUID(), "Cristi", "Ionescu", null, EmployeeRole.MEMBER));
        employees.add(new Employee(UUID.randomUUID(), "Vasile", "Popa", null, EmployeeRole.LEAD));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        /*
            for(int i = 0; i < employees.size(); i++) {
                if(employees.get(i).getUuid().equals(uuid)){
                    return employees.get(i);
                }
            }
         */
        return employees.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().get();
    }

    public Employee addEmployee(Employee employee) {
        employee.setUuid(UUID.randomUUID());
        employeeRepository.createEmployee(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee, UUID uuid) {
        return employees.stream().filter(e -> e.getUuid().equals(uuid)).map(e -> {
            e.setFirstName(employee.getFirstName());
            e.setLastName(employee.getLastName());
            e.setDateOfBirth(employee.getDateOfBirth());
            e.setRole(employee.getRole());
            return e;
        }).findFirst().get();
    }

    public Employee partiallyUpdateEmployee(Employee employee, UUID uuid) {
        return employees.stream().filter(e -> e.getUuid().equals(uuid)).map(e -> {
            if (employee.getFirstName() != null) {
                e.setFirstName(employee.getFirstName());
            }

            if (employee.getLastName() != null) {
                e.setLastName(employee.getLastName());
            }

            if (employee.getDateOfBirth() != null) {
                e.setDateOfBirth(employee.getDateOfBirth());
            }

            if (employee.getRole() != null) {
                e.setRole(employee.getRole());
            }
            return e;
        }).findFirst().get();
    }

    public void deleteEmployee(UUID uuid) {
        employees.removeIf(e -> e.getUuid().equals(uuid));
    }

    public List<Employee> filterEmployees(String criteria) {
        return employees.stream().filter(e ->
                e.getFirstName().toLowerCase(Locale.ROOT).contains(criteria) ||
                        e.getLastName().toLowerCase(Locale.ROOT).contains(criteria)).collect(Collectors.toList());
    }

}
