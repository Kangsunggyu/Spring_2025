package org.example.calendar.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String name;
    private String password;
    private Long calendarId;
}
