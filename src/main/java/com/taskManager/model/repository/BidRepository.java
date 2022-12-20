package com.taskManager.model.repository;

import com.taskManager.model.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByProducts_Department_Id(Long departmentId);
    List<Bid> findByMaterials_DepartmentMaterial_Id(Long departmentId);
}
