package org.example.calendardevelop.dto;
import lombok.Getter;
import org.example.calendardevelop.entity.UserEntity;

import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
    }
}
