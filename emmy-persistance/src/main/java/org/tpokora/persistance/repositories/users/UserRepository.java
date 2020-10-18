package org.tpokora.persistance.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tpokora.persistance.entity.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User user set user.username = :newUsername where user.id = :id")
    void updateUsername(@Param("id") int id, @Param("newUsername") String newUsername);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User user set user.email = :newEmail where user.id = :id")
    void updateEmail(@Param("id") int id, @Param("newEmail") String newEmail);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User user set user.password = :newPassword where user.id = :id")
    void updatePassword(@Param("id") int id, @Param("newPassword") String password);
}
