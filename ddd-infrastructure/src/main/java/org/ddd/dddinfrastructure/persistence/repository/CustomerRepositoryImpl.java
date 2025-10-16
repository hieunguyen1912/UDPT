package org.ddd.dddinfrastructure.persistence.repository;

import org.ddd.ddddomain.model.Customer;
import org.ddd.ddddomain.repository.CustomerRepository;
import org.ddd.dddinfrastructure.persistence.entity.CustomerEntity;
import org.ddd.dddinfrastructure.persistence.mapper.EntityMapper;
import org.ddd.dddinfrastructure.persistence.repository.jpa.CustomerJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;
    private final EntityMapper entityMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository jpaRepository, EntityMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = entityMapper.toEntity(customer);
        CustomerEntity savedEntity = jpaRepository.save(entity);
        return entityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return jpaRepository.findById(customerId)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Customer> findByNameContaining(String name) {
        return jpaRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String customerId) {
        return jpaRepository.existsById(customerId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(String customerId) {
        jpaRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    
}
