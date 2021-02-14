package com.employee.ems.service;

import com.employee.ems.dao.derivedRepositories.EmployeeRepository;
import com.employee.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getAllEntities();
    }

    public Employee getEmployee(String id) {
        return employeeRepository.getEntityById(Employee.class, id);
    }

    public Optional<Serializable> saveEmployee(Employee employee) {
        return employeeRepository.insertEntity(new Employee(
                UUID.randomUUID().toString(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDob(),
                employee.getCv(),
                employee.getSalary(),
                employee.isActive(),
                employee.getAvailableLeaveDaysPerYear(),
                employee.getAvailableSickDaysPerYear()
        ));
    }

    public boolean updateEmployee(Employee employee, String id) {
        return employeeRepository.updateEntity(new Employee(
                id,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDob(),
                employee.getCv(),
                employee.getSalary(),
                employee.isActive(),
                employee.getAvailableLeaveDaysPerYear(),
                employee.getAvailableSickDaysPerYear()
        ));
    }

    public boolean deleteEmployee(String id) {
        return employeeRepository.deleteEntity(Employee.class, id);
    }
}
