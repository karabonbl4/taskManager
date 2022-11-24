package com.taskManager.model.repository;

import com.taskManager.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByTaxNumber(Integer taxNumber);
    Customer findById(Long id);
}
