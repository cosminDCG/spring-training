package com.spring.company.service;

import com.spring.company.model.Employee;
import com.spring.company.model.EmployeeRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(UUID.randomUUID(), "Ion", "Popescu", null, EmployeeRole.MEMBER));
        employees.add(new Employee(UUID.randomUUID(), "Cristi", "Ionescu", null, EmployeeRole.MEMBER));
        employees.add(new Employee(UUID.randomUUID(), "Vasile", "Popa", null, EmployeeRole.LEAD));
    }

    public List<Employee> getAllEmployees() {
        return employees;
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

    public List<Employee> addEmployee(Employee employee) {
        employee.setUuid(UUID.randomUUID());
        employees.add(employee);
        return employees;
    }

}
