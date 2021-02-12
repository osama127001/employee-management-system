package com.employee.ems.controller;

import com.employee.ems.model.Employee;
import com.employee.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
        Optional<Employee> optionalEmployee = employeeService.insertEmployee(employee);
        return optionalEmployee.isPresent()
                ? ResponseEntity.ok(optionalEmployee.get())
                : ResponseEntity.badRequest().build();
    }

}
