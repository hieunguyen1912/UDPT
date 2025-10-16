package org.ddd.ddddomain.service;

import org.ddd.ddddomain.model.Train;
import org.ddd.ddddomain.model.TrainBooking;

public interface TrainBookingDomainService {
    
    TrainBooking createBooking(String customerId, String trainId, String seatNumber);
    
    boolean canBookSeat(Train train, String seatNumber);
    
    void validateBooking(TrainBooking booking);
    
    boolean isBookingValid(TrainBooking booking);
    
    void validateCustomer(String customerId);
    
    void validateTrain(String trainId);
}
