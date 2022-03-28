package com.spring.company.service;

import com.spring.company.model.Employee;
import com.spring.company.model.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectService {

    private List<Project> projects;

    public ProjectService() {
        projects.add(new Project(UUID.randomUUID(), "Vacation platform", "Vacation platform for internals", EmployeeService.employees));
        List<Employee> vetEmployee = new ArrayList<>();
        vetEmployee.add(EmployeeService.employees.get(0));
        vetEmployee.add(EmployeeService.employees.get(1));
        projects.add(new Project(UUID.randomUUID(), "Vet App", "App for vet medicine", vetEmployee));
        projects.add(new Project(UUID.randomUUID(), "Social App", "FB alike application", EmployeeService.employees));
    }
}
