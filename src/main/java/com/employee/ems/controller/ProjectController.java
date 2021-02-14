package com.employee.ems.controller;

import com.employee.ems.model.ErrorMessage;
import com.employee.ems.model.Project;
import com.employee.ems.service.ProjectService;
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
@RequestMapping("api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProjects() {
        Optional<List<Project>> optionalProjects = Optional.ofNullable(projectService.getAllProjects());
        return getResponseEntity(optionalProjects);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<?> getProject(@PathVariable("project_id") String id) {
        Optional<Project> optionalProject = Optional.ofNullable(projectService.getProject(id));
        return getResponseEntity(optionalProject);
    }

    @PostMapping("/")
    public ResponseEntity<?> insertProject(@RequestBody Project project) {
        Optional<Serializable> optionalResponseData = projectService.insertProject(project);
        return getResponseEntity(optionalResponseData);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable("project_id") String id) {
        Optional<Boolean> optionalUpdateProject = Optional.of(projectService.updateProject(project, id));
        return getResponseEntity(optionalUpdateProject);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<?> deleteProject(@PathVariable("project_id") String id) {
        Optional<Boolean> optionalUpdateProject = Optional.of(projectService.deleteProject(id));
        return getResponseEntity(optionalUpdateProject);
    }

    private <T> ResponseEntity<?> getResponseEntity(Optional<T> optionalProjects) {
        if (optionalProjects.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalProjects.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot get/post data!"));
    }

}
