package com.spring.company.repository;

import com.spring.company.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepositoryCRUD extends CrudRepository<Employee, UUID> {

    List<Employee> findAll();

    List<Employee> findEmployeeByFirstNameContainingOrLastNameContaining(String criteria1, String criteria2);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE CONCAT('%', :criteria, '%') OR e.lastName LIKE CONCAT('%', :criteria, '%')")
    List<Employee> filterEmployees(String criteria);
}
