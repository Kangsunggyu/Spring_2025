package org.example.calendardevelop.repository;

import org.example.calendardevelop.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity,Long> {
    List<CalendarEntity> findByUserEntity_Id(Long userId);
}
