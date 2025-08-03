package org.example.calendar.dto;

import lombok.Getter;
import org.example.calendar.entity.CommentEntity;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;

    public CommentResponseDto(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.content = commentEntity.getContent();
        this.name = commentEntity.getName();
        this.creationTime = commentEntity.getCreationTime();
        this.modificationTime = commentEntity.getModificationTime();
    }
}