package org.example.calendar.dto;

import lombok.Getter;
import org.example.calendar.entity.CalendarEntity;

import java.time.LocalDateTime;
@Getter
public class CalendarResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;

    // 생성자
    public CalendarResponseDto(CalendarEntity entityCalender) {
        this.id = entityCalender.getId();
        this.title = entityCalender.getTitle();
        this.content = entityCalender.getContent();
        this.name = entityCalender.getName();
        this.creationTime = entityCalender.getCreationTime();
        this.modificationTime = entityCalender.getModificationTime();
    }
}