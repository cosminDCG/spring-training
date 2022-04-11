package com.spring.company.repository;

import com.spring.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepositoryJPA extends CrudRepository<Employee, UUID> {

    List< Employee> findAll();
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE CONCAT('%',:criteria,'%') OR e.lastName LIKE CONCAT('%',:criteria,'%')")
    List<Employee> findByFirstNameOrLastNameContaining(@Param("criteria") String criteria);
}
