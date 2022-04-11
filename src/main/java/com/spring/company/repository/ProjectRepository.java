package com.spring.company.repository;

import com.spring.company.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends CrudRepository<Project, UUID> {

    List<Project> findAll();
}
