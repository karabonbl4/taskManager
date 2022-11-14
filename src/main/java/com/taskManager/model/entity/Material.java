package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String property;
    @Column
    private Integer value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department departmentMaterial;
}
