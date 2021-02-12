package com.employee.ems.service;

import com.employee.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmployeeService {

//    private EmployeeDao employeeDao;
//
//    @Autowired
//    public EmployeeService(EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }
//
//    @Transactional
//    public Optional<Employee> insertEmployee(Employee employee) {
//        return employeeDao.insertIntoEmployee(UUID.randomUUID(), employee);
//    }


}
