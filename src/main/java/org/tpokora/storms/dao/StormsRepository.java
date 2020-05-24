package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tpokora.storms.model.StormEntity;

import java.util.List;

public interface StormsRepository extends JpaRepository<StormEntity, Integer> {

    @Query("SELECT s from StormEntity s WHERE s.x = :x AND s.y = :y ORDER BY s.timestamp DESC")
    List<StormEntity> getStormEntitiesByCoordinatesSortByDate(@Param("x") String x, @Param("y") String y);
}
