package com.taskManager.model.entity;

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
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tasks")
    private List<Employee> employees;

//    @Override
//    public String toString() {
//        return "Task{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", deadLine=" + deadLine +
//                ", priority=" + priority +
//                ", workday=" + workday +
//                '}';
//    }
}
