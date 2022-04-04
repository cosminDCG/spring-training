package com.spring.company.repository;

import com.spring.company.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createEmployee(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee(uuid, first_name, last_name, date_of_birth, role) VALUES (?, ?, ?, ?, ?)",
                employee.getUuid().toString(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDateOfBirth(),
                employee.getRole().name());
    }

    public List<Employee> getAllEmployees() {
        String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
        return jdbcTemplate.query(GET_ALL_EMPLOYEES, new BeanPropertyRowMapper<>(Employee.class));
    }
}
