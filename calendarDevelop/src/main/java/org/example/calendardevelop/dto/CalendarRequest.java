package org.example.calendardevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 클라이언트가 입력할 데이터를 담는 객체
@Getter
public class CalendarRequest {
    @NotBlank(message = "작성자명은 필수 입력값입니다.")
    @Size(max = 30, message = "작성자명은 최대 30자까지 입력 가능합니다.")
    private String username;

    @Size(max = 30, message = "타이틀은 최대 30자까지 입력 가능합니다.")
    private String title;

    @Size(max = 200, message = "내용은 최대 200자까지 입력 가능합니다.")
    private String content;
}
