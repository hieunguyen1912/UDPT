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
public class TrainBooking {
    
    private String bookingId;
    
    // References (Foreign Keys)
    private String customerId;
    
    private String trainId;
    
    // Booking-specific info (snapshot at time of booking)
    private String seatNumber;
    
    private BigDecimal price;
    
    private LocalDateTime departureTime;
    
    // Core booking info
    private BookingStatus status;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructor for creating new booking
    public TrainBooking(String customerId, String trainId, String seatNumber, 
                       LocalDateTime departureTime, BigDecimal price) {
        this.bookingId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.departureTime = departureTime;
        this.price = price;
        this.status = BookingStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Business Methods
    public void confirm() {
        if (this.status != BookingStatus.PENDING) {
            throw new IllegalStateException("Only pending bookings can be confirmed");
        }
        this.status = BookingStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void cancel() {
        if (this.status == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled");
        }
        this.status = BookingStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isConfirmed() {
        return this.status == BookingStatus.CONFIRMED;
    }
    
    public boolean isPending() {
        return this.status == BookingStatus.PENDING;
    }
    
    public boolean isCancelled() {
        return this.status == BookingStatus.CANCELLED;
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED
    }
}
