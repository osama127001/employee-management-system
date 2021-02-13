package com.employee.ems.controller;

import com.employee.ems.model.EmployeeType;
import com.employee.ems.model.ErrorMessage;
import com.employee.ems.service.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/employeetype")
public class EmployeeTypeController {

    private final EmployeeTypeService employeeTypeService;

    @Autowired
    public EmployeeTypeController(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployeeTypes() {
        Optional<List<EmployeeType>> optionalEmployeeTypes = Optional
                .ofNullable(employeeTypeService.getAllEmployeeTypes());
        if (optionalEmployeeTypes.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalEmployeeTypes.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot get all employee types!"));
    }
}
