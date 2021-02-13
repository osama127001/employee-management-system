package com.employee.ems.controller;

import com.employee.ems.model.Department;
import com.employee.ems.model.ErrorMessage;
import com.employee.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllDepartments() {
        Optional<List<Department>> optionalDepartments = Optional.ofNullable(departmentService.getAllDepartments());
        if (optionalDepartments.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalDepartments.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot get All Departments!"));
    }


    @GetMapping("/{dept_id}")
    public ResponseEntity<?> getAllDepartments(@PathVariable("dept_id") String id) {
        Optional<Department> optionalDepartment = Optional.ofNullable(departmentService.getDepartment(id));
        if (optionalDepartment.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalDepartment.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot get Department with Id: " + id));
    }


    @PostMapping("/")
    public ResponseEntity<?> insertDepartment(@RequestBody Department department) {
        Optional<Serializable> optionalResponseDepartment = departmentService.saveDepartment(department);
        if (optionalResponseDepartment.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalResponseDepartment.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot Save Department!"));
    }

    @PutMapping("/{dept_id}")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @PathVariable("dept_id") String id) {
        if (departmentService.updateDepartment(department, id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot Update Department!"));

    }


    @DeleteMapping("/{dept_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("dept_id") String id) {
        if (departmentService.deleteDepartment(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot delete Department!"));
    }
}
