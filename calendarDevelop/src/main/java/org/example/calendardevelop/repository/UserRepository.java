package org.example.calendardevelop.repository;

import org.example.calendardevelop.entity.CalendarEntity;
import org.example.calendardevelop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByUserNameOrderByModifiedDateDesc(String name);
}
