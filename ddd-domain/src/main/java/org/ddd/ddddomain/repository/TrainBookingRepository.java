package org.ddd.ddddomain.repository;

import org.ddd.ddddomain.model.TrainBooking;

import java.util.List;
import java.util.Optional;

public interface TrainBookingRepository {
    
    TrainBooking save(TrainBooking booking);
    
    Optional<TrainBooking> findById(String bookingId);
    
    List<TrainBooking> findByCustomerId(String customerId);
    
    List<TrainBooking> findByTrainId(String trainId);
    
    List<TrainBooking> findByStatus(TrainBooking.BookingStatus status);
    
    boolean existsById(String bookingId);
    
    void deleteById(String bookingId);
    
    List<TrainBooking> findAll();
}
