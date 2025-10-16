package org.ddd.ddddomain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    private String customerId;
    
    private String name;
    
    private String email;
    
    private String phone;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructor for creating new customer
    public Customer(String name, String email, String phone) {
        this.customerId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Business Methods
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public boolean isValidPhone() {
        return phone != null && phone.matches("\\d{10,11}");
    }
    
    public void updateContactInfo(String name, String phone) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
}
