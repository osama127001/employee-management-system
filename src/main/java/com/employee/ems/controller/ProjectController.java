package com.employee.ems.controller;

import com.employee.ems.model.Employee;
import com.employee.ems.model.Project;
import com.employee.ems.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        Optional<List<Project>> projects = Optional.of(projectService.getProjects());
        if (projects.isPresent()) {
            return ResponseEntity.ok(projects.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("project_id") UUID id) {
        Optional<Project> optionalProject = projectService.getProject(id);
        if (optionalProject.isPresent()) {
            return ResponseEntity.ok(optionalProject.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Project> insertProject(@RequestBody @Valid Project project) {
        projectService.saveProject(project);
        return null;
    }
}
