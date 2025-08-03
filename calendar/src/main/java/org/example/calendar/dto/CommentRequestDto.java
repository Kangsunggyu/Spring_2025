package org.example.calendar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    @Size(max = 100, message = "댓글 내용은 최대 100자까지 입력 가능합니다.")
    private String content;

    @NotBlank(message = "작성자명은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @NotNull(message = "일정 ID는 필수 입력값입니다.")
    private Long calendarId;
}
