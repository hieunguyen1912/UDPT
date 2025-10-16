package org.ddd.ddddomain.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentProcessedEvent extends DomainEvent {
    
    private String paymentId;
    private String bookingId;
    private String amount;
    private String status;
    private String paymentMethod;
    
    public PaymentProcessedEvent() {
        super();
    }
    
    public PaymentProcessedEvent(String bookingId, String paymentId, String amount, 
                               String status, String paymentMethod) {
        super(bookingId);
        this.bookingId = bookingId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }
    
    @Override
    public String getEventType() {
        return "PAYMENT_PROCESSED";
    }
}
