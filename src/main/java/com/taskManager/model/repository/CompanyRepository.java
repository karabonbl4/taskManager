package com.taskManager.model.repository;

import com.taskManager.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByBids_Products_Department_Id(Long departmentId);
    List<Company> findByBids_Materials_DepartmentMaterial_Id(Long departmentId);

}
