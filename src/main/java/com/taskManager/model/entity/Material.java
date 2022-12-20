package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "material_storage")
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
    @Column
    private boolean deleted;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department departmentMaterial;
    @ManyToMany(mappedBy = "materials")
    private List<Bid> materialBids;

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", property='" + property + '\'' +
                ", value=" + value +
                ", department=" + departmentMaterial.getId() +
                '}';
    }
}
