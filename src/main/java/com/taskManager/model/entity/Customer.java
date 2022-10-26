package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department departmentCustomer;

}
