package com.spring.company.repository;

import com.spring.company.model.Employee;
import com.spring.company.model.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public void createEmployee(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee(uuid, first_name, last_name, date_of_birth, role) VALUES (?, ?, ?, ?, ?)",
                employee.getUuid().toString(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDateOfBirth(),
                employee.getRole().name()
        );
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE uuid = ?",
                new Object[]{uuid.toString()},
                new EmployeeMapper());
    }

    public void updateEmployee(Employee employee, UUID uuid) {
        jdbcTemplate.update("UPDATE employee SET first_name = ?, last_name = ?, date_of_birth = ?, role = ? WHERE uuid = ?",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDateOfBirth(),
                employee.getRole().name(),
                uuid.toString());
    }

    public void updateFirstName(String firstName, UUID uuid) {
        jdbcTemplate.update("UPDATE employee SET first_name = ? WHERE uuid = ?",
                firstName, uuid.toString());
    }

    public void updateLastName(String lastName, UUID uuid) {
        jdbcTemplate.update("UPDATE employee SET last_name = ? WHERE uuid = ?",
                lastName, uuid.toString());
    }

    public void updateDateOfBirth(Date dateOfBirth, UUID uuid) {
        jdbcTemplate.update("UPDATE employee SET date_of_birth = ? WHERE uuid = ?",
                dateOfBirth, uuid.toString());
    }

    public void updateRole(EmployeeRole role, UUID uuid) {
        jdbcTemplate.update("UPDATE employee SET role = ? WHERE uuid = ?",
                role.name(), uuid.toString());
    }

    public void deleteEmployee(UUID uuid) {
        jdbcTemplate.update("DELETE FROM employee WHERE uuid = ?", uuid.toString());
    }

    public List<Employee> filterEmployees(String criteria) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE first_name LIKE '%" + criteria +"%' OR last_name LIKE '%" + criteria + "%'",
                new EmployeeMapper());
        // select * from employee where first_name like '%criteria%' OR last_name like '%criteria%'
    }
}
