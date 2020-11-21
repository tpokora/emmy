package org.tpokora.persistance.repositories.console;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tpokora.persistance.entity.console.AppPropertyEntity;

import java.util.Optional;

@Repository
public interface IAppPropertyRepository extends JpaRepository<AppPropertyEntity, Integer> {

    Optional<AppPropertyEntity> findByProperty(String property);
}
