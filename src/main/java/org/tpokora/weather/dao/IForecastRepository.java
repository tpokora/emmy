package org.tpokora.weather.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.weather.model.Forecast;

import java.util.List;
import java.util.Optional;

public interface IForecastRepository extends JpaRepository<Forecast, Integer> {

    Optional<Forecast> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
    List<Forecast> findAllByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
}
