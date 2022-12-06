package com.taskManager.model.repository;

import com.taskManager.model.entity.TempMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempMaterialRepository extends JpaRepository<TempMaterial, Long> {

}
