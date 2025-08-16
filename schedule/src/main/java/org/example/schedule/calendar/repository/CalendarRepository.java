package org.example.schedule.calendar.repository;

import org.example.schedule.calendar.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    List<CalendarEntity> findByUserEntity_Id(Long userEntityId);
}
