package com.taskManager.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "temp_material")
public class TempMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String property;
    @Column
    private Integer value;
    @Column(name = "material_id")
    private Long materialId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    @Override
    public String toString() {
        return name + " - " +
                 property + " - " +
                 value + '\n';
    }
}
