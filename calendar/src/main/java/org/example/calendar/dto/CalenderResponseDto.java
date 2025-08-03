package org.example.calendar.dto;

import org.example.calendar.entity.CalenderEntity;

import java.time.LocalDateTime;

public class CalenderResponseDto {
    private int id;
    private String title;
    private String Content;
    private String name;
    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    // 생성자
    public CalenderResponseDto(CalenderEntity entityCalender) {
        this.id = entityCalender.getId();
        this.title = entityCalender.getTitle();
        this.Content = entityCalender.getContent();
        this.name = entityCalender.getName();
        this.creationTime = entityCalender.getCreationTime();
        this.modificationTime = entityCalender.getModificationTime();
    }
}