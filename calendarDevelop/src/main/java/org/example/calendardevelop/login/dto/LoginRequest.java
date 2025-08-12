package org.example.calendardevelop.login.dto;
import lombok.Getter;

// email과 password로 로그인환경을 만든다.
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
