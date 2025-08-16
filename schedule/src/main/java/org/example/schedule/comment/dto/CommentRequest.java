package org.example.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequest {
    @NotBlank(message = "댓글은 필수 입력값입니다.")
    private String comment;
}
