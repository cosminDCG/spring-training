package com.spring.company.repository;

import com.spring.company.model.Employee;
import com.spring.company.model.EmployeeRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setUuid(UUID.fromString(rs.getString("uuid")));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        employee.setRole(EmployeeRole.valueOf(rs.getString("role")));

        return employee;
    }
}
