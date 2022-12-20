package com.taskManager.model.entity;

import com.taskManager.model.enums.CompanyCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Company {
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
    @Column
    private CompanyCategory role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Bid> bids;

}
