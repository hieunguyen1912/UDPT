package org.ddd.ddddomain.repository;

import org.ddd.ddddomain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    
    Customer save(Customer customer);
    
    Optional<Customer> findById(String customerId);
    
    Optional<Customer> findByEmail(String email);
   
    List<Customer> findByNameContaining(String name);
    
    boolean existsById(String customerId);
    
    boolean existsByEmail(String email);
    
    void deleteById(String customerId);
    
    List<Customer> findAll();
}
