package org.example.calendardevelop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 클라이언트(사용자)에게 반환할 캘린더 데이터
public class CalendarResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;

    // 생성자
    public CalendarResponse(Long id, String title, String content, String userName, LocalDateTime creationTime, LocalDateTime modificationTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
    }
}
