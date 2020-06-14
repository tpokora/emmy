package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.storms.model.StormEntity;

import java.util.Optional;

public interface StormsRepository extends JpaRepository<StormEntity, Integer> {

    Optional<StormEntity> findFirstByLongitudeAndLatitudeOrderByTimestampDesc(String longitude, String latitude);
}
