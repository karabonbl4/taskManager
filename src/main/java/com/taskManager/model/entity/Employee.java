package com.taskManager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    public Employee(User user, String name, Department newDepartment) {
        this.user = user;
        this.name = name;
        this.department = newDepartment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_task", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

//    @Override
//    public String toString() {
//        return "Employee{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", userId=" + user +
//                ", tasks=" + tasks.toString() +
//                '}';
//    }
}
