package org.example.calendardevelop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequest {
    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    @Size(max = 100, message = "댓글 내용은 최대 100자까지 입력 가능합니다.")
    private String content;
}
