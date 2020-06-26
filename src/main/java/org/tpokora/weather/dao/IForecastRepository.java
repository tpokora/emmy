package org.tpokora.weather.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.weather.model.ForecastEntity;

import java.util.List;
import java.util.Optional;

public interface IForecastRepository extends JpaRepository<ForecastEntity, Integer> {

    Optional<ForecastEntity> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
    List<ForecastEntity> findAllByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
}
