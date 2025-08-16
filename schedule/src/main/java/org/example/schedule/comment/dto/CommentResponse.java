package org.example.schedule.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final Long userId;
    private final Long scheduleId;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public CommentResponse(Long id, Long userId, Long scheduleId, String content, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }
}
