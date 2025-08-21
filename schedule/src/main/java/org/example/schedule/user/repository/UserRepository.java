package org.example.schedule.user.repository;

import org.example.schedule.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
