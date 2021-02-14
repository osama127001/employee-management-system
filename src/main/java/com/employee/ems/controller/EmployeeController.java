package com.employee.ems.controller;

import com.employee.ems.model.Employee;
import com.employee.ems.model.ErrorMessage;
import com.employee.ems.service.EmployeeService;
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
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployees() {
        Optional<List<Employee>> optionalEmployees = Optional.ofNullable(employeeService.getEmployees());
        return getResponseEntity(optionalEmployees);
    }


    @GetMapping("/{employee_id}")
    public ResponseEntity<?> getEmployee(@PathVariable("employee_id") String id) {
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeService.getEmployee(id));
        return getResponseEntity(optionalEmployee);
    }

    @PostMapping("/")
    public ResponseEntity<?> insertEmployee(@RequestBody @Valid Employee employee) {
        Optional<Serializable> optionalResponseEmployee = employeeService.saveEmployee(employee);
        return getResponseEntity(optionalResponseEmployee);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee employee, @PathVariable("employee_id") String id) {
        Optional<Boolean> optionalResponseUpdate = Optional.of(employeeService.updateEmployee(employee, id));
        return getResponseEntity(optionalResponseUpdate);
    }

    @PutMapping("/{employee_id}/{project_id}")
    public ResponseEntity<?> associateEmployeeWithProject(@PathVariable("employee_id") String employeeId,
                                                          @PathVariable("project_id") String projectId) {
        Optional<Boolean> optionalResponse = Optional
                .of(employeeService.associateEmployeeWithProject(employeeId, projectId));
        return getResponseEntity(optionalResponse);
    }

    @PutMapping("set-manager/{employee_id}/{manager_id}")
    public ResponseEntity<?> assignManagerToEmployee(@PathVariable("employee_id") String employeeId,
                                                     @PathVariable("manager_id") String managerId) {
        Optional<Boolean> optionalResponse = Optional
                .of(employeeService.assignManagerToEmployee(managerId, employeeId));
        return getResponseEntity(optionalResponse);
    }

    @PutMapping("set-office/{office_id}/{employee_id}")
    public ResponseEntity<?> assignOfficeToEmployee(@PathVariable("office_id") String officeId,
                                                    @PathVariable("employee_id") String employeeId) {
        Optional<Boolean> optionalResponse = Optional
                .of(employeeService.assignOfficeToEmployee(officeId, employeeId));
        return getResponseEntity(optionalResponse);
    }

    @PutMapping("set-department/{department_id}/{employee_id}")
    public ResponseEntity<?> assignDepartmentToEmployee(@PathVariable("department_id") String departmentId,
                                                        @PathVariable("employee_id") String employeeId) {
        Optional<Boolean> optionalResponse = Optional
                .of(employeeService.assignDepartmentToEmployee(departmentId, employeeId));
        return getResponseEntity(optionalResponse);
    }

    @PutMapping("set-employee-type/{employee_type_id}/{employee_id}")
    public ResponseEntity<?> assignEmployeeTypeToEmployee(@PathVariable("employee_type_id") String employeeTypeId,
                                                          @PathVariable("employee_id") String employeeId) {
        Optional<Boolean> optionalResponse = Optional
                .of(employeeService.assignEmployeeTypeToEmployee(employeeTypeId, employeeId));
        return getResponseEntity(optionalResponse);
    }

    @DeleteMapping("/{employee_id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employee_id") String id) {
        Optional<Boolean> optionalResponseUpdate = Optional.of(employeeService.deleteEmployee(id));
        return getResponseEntity(optionalResponseUpdate);
    }

    private <T> ResponseEntity<?> getResponseEntity(Optional<T> optionalEntity) {
        if (optionalEntity.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalEntity.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot complete operation!"));
    }

}
