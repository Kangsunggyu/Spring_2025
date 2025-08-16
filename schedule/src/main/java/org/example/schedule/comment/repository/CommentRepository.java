package org.example.schedule.comment.repository;

import org.example.schedule.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByCalendarEntity_Id(Long calendarEntityId);
}
