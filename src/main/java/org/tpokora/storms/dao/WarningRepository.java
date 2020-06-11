package org.tpokora.storms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.storms.model.WarningEntity;

public interface WarningRepository extends JpaRepository<WarningEntity, Integer> {
}
