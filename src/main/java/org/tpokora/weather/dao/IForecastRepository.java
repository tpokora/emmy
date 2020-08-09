package org.tpokora.weather.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tpokora.weather.model.entity.ForecastEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IForecastRepository extends JpaRepository<ForecastEntity, Integer> {

    Optional<ForecastEntity> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
    List<ForecastEntity> findAllByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);

    @Query(value = "from ForecastEntity t where longitude = :longitude and latitude = :latitude and timestamp BETWEEN :startDate and :endDate")
    List<ForecastEntity> findAllByLongitudeAndLatitudeAndOrderByTimestampDesc(@Param("longitude") double longitude,
                                                                              @Param("latitude") double latitude,
                                                                              @Param("startDate") LocalDateTime startDate,
                                                                              @Param("endDate") LocalDateTime endDate);
}
