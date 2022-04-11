package com.spring.company.controller;

import com.spring.company.model.Project;
import com.spring.company.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getEmployeeByUuid(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProjectByUuid(UUID.fromString(id)));
    }

    @PostMapping
    public ResponseEntity<Project> addEmployee(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }
}
