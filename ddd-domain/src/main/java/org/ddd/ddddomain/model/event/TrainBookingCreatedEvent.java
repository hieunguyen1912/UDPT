package org.ddd.ddddomain.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainBookingCreatedEvent extends DomainEvent {
    
    private String bookingId;
    private String customerId;
    private String trainId;
    private String seatNumber;
    private LocalDateTime departureTime;
    private BigDecimal price;
    
    public TrainBookingCreatedEvent() {
        super();
    }
    
    public TrainBookingCreatedEvent(String bookingId, String customerId, String trainId, 
                                  String seatNumber, LocalDateTime departureTime, BigDecimal price) {
        super(bookingId);
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.departureTime = departureTime;
        this.price = price;
    }
    
    @Override
    public String getEventType() {
        return "TRAIN_BOOKING_CREATED";
    }
}
