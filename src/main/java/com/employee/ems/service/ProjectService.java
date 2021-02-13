package com.employee.ems.service;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.dao.interfaces.GenericRepository;
import com.employee.ems.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final GenericRepository<Project> genericRepository;

    @Autowired
    public ProjectService(GenericRepository<Project> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public List<Project> getProjects() {
        return genericRepository.getAllEntities(Project.class);
    }

    public Optional<Project> getProject(UUID id) {
        ///return genericRepository.getEntityById(Project.class, id);
        return Optional.empty();
    }

    public Optional<Serializable> saveProject(Project project) {
        genericRepository.insertEntity(
            new Project(
                UUID.randomUUID(),
                project.getName(),
                project.getDescription()
            )
        );
        return Optional.empty();
    }

}
