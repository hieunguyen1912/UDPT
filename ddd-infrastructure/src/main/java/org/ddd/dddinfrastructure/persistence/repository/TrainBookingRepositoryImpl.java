package org.ddd.dddinfrastructure.persistence.repository;

import org.ddd.ddddomain.model.TrainBooking;
import org.ddd.ddddomain.repository.TrainBookingRepository;
import org.ddd.dddinfrastructure.persistence.entity.TrainBookingEntity;
import org.ddd.dddinfrastructure.persistence.mapper.EntityMapper;
import org.ddd.dddinfrastructure.persistence.repository.jpa.TrainBookingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TrainBookingRepositoryImpl implements TrainBookingRepository {

    private final TrainBookingJpaRepository jpaRepository;
    private final EntityMapper entityMapper;

    public TrainBookingRepositoryImpl(TrainBookingJpaRepository jpaRepository, EntityMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public TrainBooking save(TrainBooking booking) {
        TrainBookingEntity entity = entityMapper.toEntity(booking);
        TrainBookingEntity savedEntity = jpaRepository.save(entity);
        return entityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<TrainBooking> findById(String bookingId) {
        return jpaRepository.findById(bookingId)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<TrainBooking> findByCustomerId(String customerId) {
        return jpaRepository.findByCustomerId(customerId)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainBooking> findByTrainId(String trainId) {
        return jpaRepository.findByTrainId(trainId)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainBooking> findByStatus(TrainBooking.BookingStatus status) {
        TrainBookingEntity.BookingStatus entityStatus = mapToEntityStatus(status);
        return jpaRepository.findByStatus(entityStatus)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String bookingId) {
        return jpaRepository.existsById(bookingId);
    }

    @Override
    public void deleteById(String bookingId) {
        jpaRepository.deleteById(bookingId);
    }

    @Override
    public List<TrainBooking> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    private TrainBookingEntity.BookingStatus mapToEntityStatus(TrainBooking.BookingStatus domainStatus) {
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

    
}
