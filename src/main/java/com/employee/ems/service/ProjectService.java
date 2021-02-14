package com.employee.ems.service;

import com.employee.ems.dao.derivedRepositories.ProjectRepository;
import com.employee.ems.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllEntities();
    }


    public Project getProject(String id) {
        return projectRepository.getEntityById(Project.class, id);
    }

    public Optional<Serializable> insertProject(Project project) {
        return projectRepository.insertEntity(new Project(
                UUID.randomUUID().toString(),
                project.getName(),
                project.getDescription()
        ));
    }

    public boolean updateProject(Project project, String id) {
        return projectRepository.updateEntity(new Project(
                id,
                project.getName(),
                project.getDescription()
        ));
    }

    public boolean deleteProject(String id) {
        return projectRepository.deleteEntity(Project.class, id);
    }



}
