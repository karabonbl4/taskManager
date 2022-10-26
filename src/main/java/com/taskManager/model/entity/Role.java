package com.taskManager.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {
    public Role(String name){
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    @Override
    public String getAuthority() {
        return getName();
    }
}
