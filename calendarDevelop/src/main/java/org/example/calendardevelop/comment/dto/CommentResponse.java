package org.example.calendardevelop.comment.dto;

import lombok.Getter;
import org.example.calendardevelop.comment.entity.CommentEntity;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public CommentResponse(CommentEntity comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
