package org.example.calendardevelop.comment.repository;

import org.example.calendardevelop.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByCalendarEntityId(Long calendarEntityId);
}
