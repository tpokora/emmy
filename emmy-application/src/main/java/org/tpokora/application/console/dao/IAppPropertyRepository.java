package org.tpokora.application.console.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.application.console.model.entity.AppPropertyEntity;

import java.util.Optional;

public interface IAppPropertyRepository extends JpaRepository<AppPropertyEntity, Integer> {

    Optional<AppPropertyEntity> findByProperty(String property);
}
