package org.example.calendar.dto;

import lombok.Getter;
import org.example.calendar.entity.CalendarEntity;
import org.example.calendar.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CalendarResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;
    private final List<CommentResponseDto> comments;

    // 생성자
    public CalendarResponseDto(CalendarEntity entityCalender) {
        this.id = entityCalender.getId();
        this.title = entityCalender.getTitle();
        this.content = entityCalender.getContent();
        this.name = entityCalender.getName();
        this.creationTime = entityCalender.getCreationTime();
        this.modificationTime = entityCalender.getModificationTime();

        List<CommentResponseDto> comments = new ArrayList<>();
        for (CommentEntity commentEntity : entityCalender.getComments()) {
            comments.add(new CommentResponseDto(commentEntity));
        }
        this.comments = comments;
    }
}