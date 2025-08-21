package org.example.calendar.repository;

import org.example.calendar.entity.CalendarEntity;
import org.example.calendar.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    List<CalendarEntity> findByNameOrderByModificationTime(String name);
    Page<CalendarEntity> findByCalendarId(Long postId, Pageable pageable);
}
