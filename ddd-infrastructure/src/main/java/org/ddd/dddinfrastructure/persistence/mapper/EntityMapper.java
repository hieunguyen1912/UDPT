package org.ddd.dddinfrastructure.persistence.mapper;

import org.ddd.ddddomain.model.Customer;
import org.ddd.ddddomain.model.Train;
import org.ddd.ddddomain.model.TrainBooking;
import org.ddd.dddinfrastructure.persistence.entity.CustomerEntity;
import org.ddd.dddinfrastructure.persistence.entity.TrainBookingEntity;
import org.ddd.dddinfrastructure.persistence.entity.TrainEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    // TrainBooking Mappers
    public TrainBookingEntity toEntity(TrainBooking domain) {
        if (domain == null) return null;
        
        TrainBookingEntity entity = new TrainBookingEntity();
        entity.setBookingId(domain.getBookingId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setTrainId(domain.getTrainId());
        entity.setSeatNumber(domain.getSeatNumber());
        entity.setPrice(domain.getPrice());
        entity.setDepartureTime(domain.getDepartureTime());
        entity.setStatus(mapBookingStatus(domain.getStatus()));
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public TrainBooking toDomain(TrainBookingEntity entity) {
        if (entity == null) return null;
        
        TrainBooking domain = new TrainBooking();
        domain.setBookingId(entity.getBookingId());
        domain.setCustomerId(entity.getCustomerId());
        domain.setTrainId(entity.getTrainId());
        domain.setSeatNumber(entity.getSeatNumber());
        domain.setPrice(entity.getPrice());
        domain.setDepartureTime(entity.getDepartureTime());
        domain.setStatus(mapBookingStatus(entity.getStatus()));
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    // Customer Mappers
    public CustomerEntity toEntity(Customer domain) {
        if (domain == null) return null;
        
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(domain.getCustomerId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;
        
        Customer domain = new Customer();
        domain.setCustomerId(entity.getCustomerId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setPhone(entity.getPhone());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    // Train Mappers
    public TrainEntity toEntity(Train domain) {
        if (domain == null) return null;
        
        TrainEntity entity = new TrainEntity();
        entity.setTrainId(domain.getTrainId());
        entity.setTrainNumber(domain.getTrainNumber());
        entity.setFromStation(domain.getFromStation());
        entity.setToStation(domain.getToStation());
        entity.setDepartureTime(domain.getDepartureTime());
        entity.setArrivalTime(domain.getArrivalTime());
        entity.setBasePrice(domain.getBasePrice());
        entity.setTotalSeats(domain.getTotalSeats());
        entity.setAvailableSeats(domain.getAvailableSeats());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Train toDomain(TrainEntity entity) {
        if (entity == null) return null;
        
        Train domain = new Train();
        domain.setTrainId(entity.getTrainId());
        domain.setTrainNumber(entity.getTrainNumber());
        domain.setFromStation(entity.getFromStation());
        domain.setToStation(entity.getToStation());
        domain.setDepartureTime(entity.getDepartureTime());
        domain.setArrivalTime(entity.getArrivalTime());
        domain.setBasePrice(entity.getBasePrice());
        domain.setTotalSeats(entity.getTotalSeats());
        domain.setAvailableSeats(entity.getAvailableSeats());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    // Helper methods for enum mapping
    private TrainBookingEntity.BookingStatus mapBookingStatus(TrainBooking.BookingStatus domainStatus) {
        if (domainStatus == null) return null;
        
        switch (domainStatus) {
            case PENDING:
                return TrainBookingEntity.BookingStatus.PENDING;
            case CONFIRMED:
                return TrainBookingEntity.BookingStatus.CONFIRMED;
            case CANCELLED:
                return TrainBookingEntity.BookingStatus.CANCELLED;
            default:
                throw new IllegalArgumentException("Unknown booking status: " + domainStatus);
        }
    }

    private TrainBooking.BookingStatus mapBookingStatus(TrainBookingEntity.BookingStatus entityStatus) {
        if (entityStatus == null) return null;
        
        switch (entityStatus) {
            case PENDING:
                return TrainBooking.BookingStatus.PENDING;
            case CONFIRMED:
                return TrainBooking.BookingStatus.CONFIRMED;
            case CANCELLED:
                return TrainBooking.BookingStatus.CANCELLED;
            default:
                throw new IllegalArgumentException("Unknown booking status: " + entityStatus);
        }
    }
}
