package org.tpokora.persistance.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.persistance.entity.users.Role;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
