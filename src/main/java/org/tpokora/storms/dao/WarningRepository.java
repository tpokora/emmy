package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tpokora.storms.model.WarningEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WarningRepository extends JpaRepository<WarningEntity, Integer> {

    @Query(value = "select w from WarningEntity w where w.name = :name and w.level = :level and w.start = :start and w.end = :end")
    Optional<WarningEntity> findSameWarning(String name, int level, LocalDateTime start, LocalDateTime end);
}
