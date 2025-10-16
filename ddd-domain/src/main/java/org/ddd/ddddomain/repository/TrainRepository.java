package org.ddd.ddddomain.repository;

import org.ddd.ddddomain.model.Train;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainRepository {
    
    Train save(Train train);
    
    Optional<Train> findById(String trainId);
    
    List<Train> findByFromStationAndToStation(String fromStation, String toStation);
    
    List<Train> findByDepartureTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    List<Train> findTrainsWithAvailableSeats();
    
    Optional<Train> findByTrainNumber(String trainNumber);
    
    boolean existsById(String trainId);
    
    boolean existsByTrainNumber(String trainNumber);
    
    void deleteById(String trainId);
    
    List<Train> findAll();
}
