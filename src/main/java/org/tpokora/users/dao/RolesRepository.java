package org.tpokora.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.users.model.Role;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
