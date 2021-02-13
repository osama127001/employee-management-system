package com.employee.ems.service;

import com.employee.ems.dao.derivedRepositories.EmployeeTypeRepository;
import com.employee.ems.model.EmployeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    public EmployeeTypeService(EmployeeTypeRepository employeeTypeRepository) {
        this.employeeTypeRepository = employeeTypeRepository;
    }

    public List<EmployeeType> getAllEmployeeTypes() {
        return employeeTypeRepository.getAllEntities();
    }

    public EmployeeType getEmployeeType(String id) {
        return employeeTypeRepository.getEntityById(EmployeeType.class, id);
    }

    public Optional<Serializable> saveEmployeeType(EmployeeType employeeType) {
        return employeeTypeRepository.insertEntity(new EmployeeType(
                UUID.randomUUID().toString(),
                employeeType.getName(),
                employeeType.getDescription()
        ));
    }

    public boolean updateEmployeeType(EmployeeType employeeType, String id) {
        return employeeTypeRepository.updateEntity(new EmployeeType(
                id,
                employeeType.getName(),
                employeeType.getDescription()
        ));
    }

    public boolean deleteEmployeeType(String id) {
        return employeeTypeRepository.deleteEntity(EmployeeType.class, id);
    }

}
