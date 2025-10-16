package org.ddd.dddinfrastructure.persistence.repository.jpa;

import java.util.List;

import org.ddd.dddinfrastructure.persistence.entity.TrainBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainBookingJpaRepository extends JpaRepository<TrainBookingEntity, String> {
    List<TrainBookingEntity> findByCustomerId(String customerId);
    List<TrainBookingEntity> findByTrainId(String trainId);
    List<TrainBookingEntity> findByStatus(TrainBookingEntity.BookingStatus status);
}
