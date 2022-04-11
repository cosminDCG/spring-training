package com.spring.company.controller;

import com.spring.company.model.Employee;
import com.spring.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeByUuid(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.getEmployeeByUuid(UUID.fromString(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> filterEmployees(@RequestParam String criteria) {
        return ResponseEntity.ok(employeeService.filterEmployees(criteria));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable String id) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, UUID.fromString(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> partiallyUpdateEmployee(@RequestBody Employee employee, @PathVariable String id) {
        return ResponseEntity.ok(employeeService.partiallyUpdateEmployee(employee, UUID.fromString(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(UUID.fromString(id));
        return ResponseEntity.ok("Employee deleted!");
    }
}
