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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentEmployee")
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentCustomer")
    private List<Customer> customers;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentMaterial")
    private List<Material> materials;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentProvider")
    private List<Provider> providers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentTask")
    private List<Task> tasks;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", customers=" + customers +
                ", user=" + user +
                ", materials=" + materials +
                ", providers=" + providers +
                ", tasks=" + tasks +
                '}';
    }
}
