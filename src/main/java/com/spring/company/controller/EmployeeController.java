package com.spring.company.controller;

import com.spring.company.dto.EmployeeDto;
import com.spring.company.mapper.EmployeeMapper;
import com.spring.company.model.Employee;
import com.spring.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees.stream().map(employeeMapper::mapToDto).collect(Collectors.toList()));
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
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEntity(employeeDto);
        return ResponseEntity.ok(employeeMapper.mapToDto(employeeService.addEmployee(employee)));
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
