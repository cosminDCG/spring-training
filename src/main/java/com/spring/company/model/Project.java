package com.spring.company.model;

import java.util.List;
import java.util.UUID;

public class Project {

    private UUID uuid;

    private String name;

    private String description;

    private List<Employee> employees;

    public Project() {
    }

    public Project(UUID uuid, String name, String description, List<Employee> employees) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
