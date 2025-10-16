package org.ddd.ddddomain.service.impl;

import org.ddd.ddddomain.model.Train;
import org.ddd.ddddomain.model.TrainBooking;
import org.ddd.ddddomain.service.TrainBookingDomainService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TrainBookingDomainServiceImpl implements TrainBookingDomainService {

    @Override
    public TrainBooking createBooking(String customerId, String trainId, String seatNumber) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (trainId == null || trainId.trim().isEmpty()) {
            throw new IllegalArgumentException("Train ID cannot be null or empty");
        }
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be null or empty");
        }

        TrainBooking booking = new TrainBooking();
        booking.setCustomerId(customerId);
        booking.setTrainId(trainId);
        booking.setSeatNumber(seatNumber);
        booking.setDepartureTime(LocalDateTime.now().plusDays(1));
        booking.setPrice(new BigDecimal("150000"));
        booking.setStatus(TrainBooking.BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        return booking;
    }

    @Override
    public boolean canBookSeat(Train train, String seatNumber) {
        if (train == null) {
            return false;
        }
        
        if (!train.hasAvailableSeats()) {
            return false;
        }
        
        if (train.isDeparted()) {
            return false;
        }
        
        return true;
    }

    @Override
    public void validateBooking(TrainBooking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        
        if (booking.getCustomerId() == null || booking.getCustomerId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required");
        }
        
        if (booking.getTrainId() == null || booking.getTrainId().trim().isEmpty()) {
            throw new IllegalArgumentException("Train ID is required");
        }
        
        if (booking.getSeatNumber() == null || booking.getSeatNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number is required");
        }
        
        if (booking.getPrice() == null || booking.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        
        if (booking.getDepartureTime() == null) {
            throw new IllegalArgumentException("Departure time is required");
        }
        
        if (booking.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Departure time must be in the future");
        }
    }

    @Override
    public boolean isBookingValid(TrainBooking booking) {
        try {
            validateBooking(booking);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void validateCustomer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
    }

    @Override
    public void validateTrain(String trainId) {
        if (trainId == null || trainId.trim().isEmpty()) {
            throw new IllegalArgumentException("Train ID cannot be null or empty");
        }
    }
}
