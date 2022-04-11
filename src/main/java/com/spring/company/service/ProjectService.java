package com.spring.company.service;

import com.spring.company.model.Employee;
import com.spring.company.model.Project;
import com.spring.company.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(Project project) {
        project.setUuid(UUID.randomUUID());
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectByUuid(UUID uuid) {
        return projectRepository.getById(uuid);
    }
}
