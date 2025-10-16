package org.ddd.dddinfrastructure.persistence.repository;

import org.ddd.ddddomain.model.Train;
import org.ddd.ddddomain.repository.TrainRepository;
import org.ddd.dddinfrastructure.persistence.entity.TrainEntity;
import org.ddd.dddinfrastructure.persistence.mapper.EntityMapper;
import org.ddd.dddinfrastructure.persistence.repository.jpa.TrainJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TrainRepositoryImpl implements TrainRepository {

    private final TrainJpaRepository jpaRepository;
    private final EntityMapper entityMapper;

    public TrainRepositoryImpl(TrainJpaRepository jpaRepository, EntityMapper entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Train save(Train train) {
        TrainEntity entity = entityMapper.toEntity(train);
        TrainEntity savedEntity = jpaRepository.save(entity);
        return entityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Train> findById(String trainId) {
        return jpaRepository.findById(trainId)
                .map(entityMapper::toDomain);
    }

    @Override
    public List<Train> findByFromStationAndToStation(String fromStation, String toStation) {
        return jpaRepository.findByFromStationAndToStation(fromStation, toStation)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Train> findByDepartureTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return jpaRepository.findByDepartureTimeBetween(startTime, endTime)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Train> findTrainsWithAvailableSeats() {
        return jpaRepository.findByAvailableSeatsGreaterThan(0)
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Train> findByTrainNumber(String trainNumber) {
        return jpaRepository.findByTrainNumber(trainNumber)
                .map(entityMapper::toDomain);
    }

    @Override
    public boolean existsById(String trainId) {
        return jpaRepository.existsById(trainId);
    }

    @Override
    public boolean existsByTrainNumber(String trainNumber) {
        return jpaRepository.existsByTrainNumber(trainNumber);
    }

    @Override
    public void deleteById(String trainId) {
        jpaRepository.deleteById(trainId);
    }

    @Override
    public List<Train> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
