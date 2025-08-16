package org.example.schedule.user.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public UserResponse(Long id, String userName, String email, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}