package org.example.schedule.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "유저명은 필수 입력입니다.")
    private String userName;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email
    private String email;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Size(min = 5, max = 10, message = "비밀번호는 5자 이상 10자 이하이어야 합니다.")
    private String password;
}
