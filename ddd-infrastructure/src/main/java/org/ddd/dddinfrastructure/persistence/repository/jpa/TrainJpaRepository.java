package org.ddd.dddinfrastructure.persistence.repository.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.ddd.dddinfrastructure.persistence.entity.TrainEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<TrainEntity, String> {
    List<TrainEntity> findByFromStationAndToStation(String fromStation, String toStation);
    List<TrainEntity> findByDepartureTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    List<TrainEntity> findByAvailableSeatsGreaterThan(Integer availableSeats);
    Optional<TrainEntity> findByTrainNumber(String trainNumber);
    boolean existsByTrainNumber(String trainNumber);
}