package org.example.calendar.dto;

import jakarta.validation.constraints.NotBlank; // @NotBlank 어노테이션을 위한 import
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CalendarRequestDto {
    @Size(max = 30, message = "제목은 최대 30자까지 입력 가능합니다.")
    private String title;

    @Size(max = 200, message = "내용은 최대 200자까지 입력 가능합니다.")
    private String content;

    @NotBlank(message = "작성자명은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}