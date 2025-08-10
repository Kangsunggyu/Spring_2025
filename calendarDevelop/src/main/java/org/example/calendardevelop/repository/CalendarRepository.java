package org.example.calendardevelop.repository;

import org.example.calendardevelop.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    List<Calendar> findByUserNameOrderByModificationDate(String name);
}
