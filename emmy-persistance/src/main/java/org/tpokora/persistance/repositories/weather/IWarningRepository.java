package org.tpokora.persistance.repositories.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tpokora.persistance.entity.weather.WarningEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IWarningRepository extends JpaRepository<WarningEntity, Integer> {

    @Query(value = "select w from WarningEntity w where w.longitude = :lon and w.latitude = :lat and w.name = :name and w.level = :level and w.start = :start and w.end = :end")
    Optional<WarningEntity> findSameWarning(@Param("lon") double lon,
                                            @Param("lat")double lat,
                                            @Param("name")String name,
                                            @Param("level") int level,
                                            @Param("start") LocalDateTime start,
                                            @Param("end")LocalDateTime end);
}
