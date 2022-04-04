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
        return employeeRepository.getEmployeeByUuid(uuid);
    }

    public Employee addEmployee(Employee employee) {
        employee.setUuid(UUID.randomUUID());
        employeeRepository.createEmployee(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee, UUID uuid) {
        employeeRepository.updateEmployee(employee, uuid);
        return employee;
    }

    public Employee partiallyUpdateEmployee(Employee employee, UUID uuid) {
        if (employee.getFirstName() != null) {
            employeeRepository.updateFirstName(employee.getFirstName(), uuid);
        }

        if (employee.getLastName() != null) {
           employeeRepository.updateLastName(employee.getLastName(), uuid);
        }

        if (employee.getDateOfBirth() != null) {
            employeeRepository.updateDateOfBirth(employee.getDateOfBirth(), uuid);
        }

        if (employee.getRole() != null) {
            employeeRepository.updateRole(employee.getRole(), uuid);
        }
        return employeeRepository.getEmployeeByUuid(uuid);
    }

    public void deleteEmployee(UUID uuid) {
        employeeRepository.deleteEmployee(uuid);
    }

    public List<Employee> filterEmployees(String criteria) {
        return employeeRepository.filterEmployees(criteria);
    }

}
