package org.example.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String username;
    private final String password;

    public UserResponseDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
