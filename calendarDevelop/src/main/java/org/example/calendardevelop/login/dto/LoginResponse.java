package org.example.calendardevelop.login.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String email;

    LoginResponse(String email) {
        this.email = email;
    }
}
