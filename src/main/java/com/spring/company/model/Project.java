package com.spring.company.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Project {

    @Id
    @Type(type="uuid-char")
    private UUID uuid;

    private String name;

    private String description;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
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
