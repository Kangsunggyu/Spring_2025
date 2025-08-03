package org.example.calendar.dto;

import lombok.Getter;
import org.example.calendar.entity.CalenderEntity;

import java.time.LocalDateTime;
@Getter
public class CalenderResponseDto {
    private final int id;
    private final String title;
    private final String Content;
    private final String name;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;

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