package org.example.schedule.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponse {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public CalendarResponse(Long id, Long userId, String title, String content, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
