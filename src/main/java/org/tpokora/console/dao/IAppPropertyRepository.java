package org.tpokora.console.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.console.model.entity.AppPropertyEntity;

import java.util.Optional;

public interface IAppPropertyRepository extends JpaRepository<AppPropertyEntity, Integer> {

    Optional<AppPropertyEntity> findByProperty(String property);
}
