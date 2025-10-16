package org.ddd.ddddomain.service;

import org.ddd.ddddomain.model.event.DomainEvent;

public interface EventPublisher {
    
    void publish(DomainEvent event);
    
    void publish(String topic, DomainEvent event);
}
