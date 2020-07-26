package org.tpokora.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tpokora.weather.model.entity.WarningEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IWarningRepository extends JpaRepository<WarningEntity, Integer> {

    @Query(value = "select w from WarningEntity w where w.longitude = :lon and w.latitude = :lat and w.name = :name and w.level = :level and w.start = :start and w.end = :end")
    Optional<WarningEntity> findSameWarning(double lon, double lat, String name, int level, LocalDateTime start, LocalDateTime end);
}
