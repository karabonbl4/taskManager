package com.taskManager.model.repository;

import com.taskManager.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByTaxNumber(Integer taxNumber);
}
