package org.ddd.ddddomain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    
    private String trainId;
    
    private String trainNumber;
    
    private String fromStation;
    
    private String toStation;
    
    private LocalDateTime departureTime;
    
    private LocalDateTime arrivalTime;
    
    private BigDecimal basePrice;
    
    private Integer totalSeats;
    
    private Integer availableSeats;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructor for creating new train
    public Train(String trainNumber, String fromStation, String toStation, 
                LocalDateTime departureTime, LocalDateTime arrivalTime, 
                BigDecimal basePrice, Integer totalSeats) {
        this.trainId = UUID.randomUUID().toString();
        this.trainNumber = trainNumber;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.basePrice = basePrice;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Business Methods
    public boolean hasAvailableSeats() {
        return availableSeats != null && availableSeats > 0;
    }
    
    public boolean canBookSeat() {
        return hasAvailableSeats() && !isDeparted();
    }
    
    public void bookSeat() {
        if (!canBookSeat()) {
            throw new IllegalStateException("Cannot book seat - no available seats or train already departed");
        }
        this.availableSeats--;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void cancelSeat() {
        if (availableSeats >= totalSeats) {
            throw new IllegalStateException("Cannot cancel seat - no seats booked");
        }
        this.availableSeats++;
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isDeparted() {
        return LocalDateTime.now().isAfter(departureTime);
    }
    
    public BigDecimal calculatePrice() {
        // Simple pricing logic - can be enhanced with dynamic pricing
        return basePrice;
    }
    
    public boolean isSameRoute(String fromStation, String toStation) {
        return this.fromStation.equals(fromStation) && this.toStation.equals(toStation);
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
}
