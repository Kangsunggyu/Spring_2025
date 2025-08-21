package org.example.calendar.repository;

import org.example.calendar.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    long countByCalendarId(Long calendarId);

    Page<CommentEntity> findByCalendar_Id(Long postId, Pageable pageable);
}

