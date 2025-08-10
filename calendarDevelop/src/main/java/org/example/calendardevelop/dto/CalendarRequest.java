package org.example.calendardevelop.dto;

import lombok.Getter;

@Getter
// 클라이언트(사용자)로부터 받는 캘린더 데이터
public class CalendarRequest {
    private String userName;
    private String title;
    private String content;
}
