package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tpokora.storms.model.StormEntity;

import java.util.List;

public interface StormsRepository extends JpaRepository<StormEntity, Integer> {

//    @Query("select s from StormEntity where s.x = :x and s.y = :y")
//    List<StormEntity> getStormEntitiesByCoordinatesSortByDate(@Param("x") String x, @Param("y") String y);
}
