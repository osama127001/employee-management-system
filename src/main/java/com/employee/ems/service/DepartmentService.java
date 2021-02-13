package com.employee.ems.service;

import com.employee.ems.dao.derivedRepositories.DepartmentRepository;
import com.employee.ems.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllEntities();
    }

    public Department getDepartment(String id) {
        return departmentRepository.getEntityById(Department.class, id);
    }

    public Optional<Serializable> saveDepartment(Department department) {
        return departmentRepository.insertEntity(
                new Department(
                        UUID.randomUUID().toString(),
                        department.getName(),
                        department.getDescription()
                )
        );
    }

    public boolean updateDepartment(Department department, String id) {
        return departmentRepository.updateEntity(
                new Department(
                        id,
                        department.getName(),
                        department.getDescription()
                )
        );
    }

    public boolean deleteDepartment(String id) {
        return departmentRepository.deleteEntity(Department.class, id);
    }
}
