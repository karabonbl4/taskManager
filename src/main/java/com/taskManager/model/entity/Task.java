package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private StringBuilder description;
    @Column
    private Timestamp deadLine;
    @Column
    private int priority;
    @Column
    private Date workday;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tasks")
    private List<Employee> employees;


}
