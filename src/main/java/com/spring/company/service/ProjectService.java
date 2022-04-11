package com.spring.company.service;

import com.spring.company.model.Project;
import com.spring.company.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        project.setUuid(UUID.randomUUID());
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectByUuid(UUID uuid) {
        return projectRepository.findById(uuid).get();
    }

    public Project updateProject(Project project, UUID uuid) {
        project.setUuid(uuid);
        return  projectRepository.save(project);
    }

    public Project partiallyUpdateProject(Project project, UUID uuid) {
        Project oldObject = projectRepository.findById(uuid).get();

        if (project.getName() != null) {
            oldObject.setName(project.getName());
        }

        if (project.getDescription() != null) {
            oldObject.setDescription(project.getDescription());
        }

        return projectRepository.save(oldObject);
    }

    public void deleteProject(UUID uuid) {
        Project project = projectRepository.findById(uuid).get();
        projectRepository.delete(project);
    }
}
