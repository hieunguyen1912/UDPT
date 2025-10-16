package org.ddd.ddddomain.model.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class DomainEvent {
    
    private String eventId;
    private String eventType;
    private LocalDateTime timestamp;
    private String aggregateId;
    
    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }
    
    protected DomainEvent(String aggregateId) {
        this();
        this.aggregateId = aggregateId;
    }
    
    public abstract String getEventType();
}
