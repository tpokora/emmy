package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.storms.model.StormEntity;

import java.util.List;
import java.util.Optional;

public interface IStormsRepository extends JpaRepository<StormEntity, Integer> {

    Optional<StormEntity> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);

    List<StormEntity> findAllByLongitudeAndLatitudeOrderByTimestampDesc(double longitude, double latitude);
}
