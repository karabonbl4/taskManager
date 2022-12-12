package com.taskManager.model.entity;

import com.taskManager.model.enumeration.State;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Date deadLine;
    @Column
    private Integer priority;
    @Column
    private Date workday;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "flag")
    private State condition;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tasks")
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "task")
    private List<TempMaterial> tempMaterials;

}
