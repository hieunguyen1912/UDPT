package org.ddd.dddinfrastructure.persistence.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.ddd.dddinfrastructure.persistence.entity.CustomerEntity;

public interface CustomerJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);
    List<CustomerEntity> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
}