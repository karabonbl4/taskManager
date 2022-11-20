package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
    private List<Customer> customers;
    @Column
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentMaterial")
    private List<Material> materials;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentProvider")
    private List<Provider> providers;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees.toString() +
                ", location=" + location +
                ", customers=" + customers.toString() +
                ", materials=" + materials.toString() +
                ", providers=" + providers.toString() +
                '}';
    }
}
