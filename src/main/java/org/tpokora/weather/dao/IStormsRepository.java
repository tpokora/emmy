package org.tpokora.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.weather.model.entity.StormEntity;

import java.util.List;
import java.util.Optional;

public interface IStormsRepository extends JpaRepository<StormEntity, Integer> {

    Optional<StormEntity> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);

    List<StormEntity> findAllByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
}
