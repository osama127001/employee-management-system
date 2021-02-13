package com.employee.ems.controller;

import com.employee.ems.model.EmployeeType;
import com.employee.ems.model.ErrorMessage;
import com.employee.ems.service.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/employee-type")
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
        return getResponseEntity(optionalEmployeeTypes);
    }

    @GetMapping("/{eType_id}")
    public ResponseEntity<?> getEmployeeType(@PathVariable("eType_id") String id) {
        Optional<EmployeeType> optionalEmployeeType = Optional.ofNullable(employeeTypeService.getEmployeeType(id));
        return getResponseEntity(optionalEmployeeType);
    }

    @PostMapping("/")
    public ResponseEntity<?> insertEmployeeType(@RequestBody @Valid EmployeeType employeeType) {
        Optional<Serializable> optionalEmployeeTypeResponse = employeeTypeService.saveEmployeeType(employeeType);
        return getResponseEntity(optionalEmployeeTypeResponse);
    }

    @PutMapping("/{eType_id}")
    public ResponseEntity<?> updateEmployeeType(@RequestBody @Valid EmployeeType employeeType,
                                                @PathVariable("eType_id") String id) {
        if (employeeTypeService.updateEmployeeType(employeeType, id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot Update Employee-type with id: " + id));
    }

    @DeleteMapping("/{eType_id}")
    public ResponseEntity<?> deleteEmployeeType(@PathVariable("eType_id") String id) {
        if (employeeTypeService.deleteEmployeeType(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot Delete Employee-type with id: " + id));
    }

    private <T> ResponseEntity<?> getResponseEntity(Optional<T> optionalTypeObject) {
        if (optionalTypeObject.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalTypeObject.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot Complete Operation!"));
    }


}
