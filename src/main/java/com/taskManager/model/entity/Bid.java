package com.taskManager.model.entity;

import com.taskManager.model.enums.ContractType;
import com.taskManager.model.enums.State;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int value;
    @Column
    private ContractType contractType;
    @Column
    private State state;
    @Column(name = "contract_day")
    private Date contractDay;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "material_bid", joinColumns = {@JoinColumn(name = "bid_id")}, inverseJoinColumns = {@JoinColumn(name = "material_id")})
    private List<Material> materials;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "product_bid", joinColumns = {@JoinColumn(name = "bid_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
}
