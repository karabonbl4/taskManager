package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(name = "tax_number")
    private Integer taxNumber;
    @Column
    private String location;
    @Column
    private String owner;
    @Column
    private String email;
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Material> materials;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department departmentProvider;
}