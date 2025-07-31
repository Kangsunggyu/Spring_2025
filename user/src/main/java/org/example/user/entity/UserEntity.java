package org.example.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id // 기본 키, 유저 이름을 pk로 사용해본다.
    @Column(nullable = false, unique = true, length = 20)
    private String username;
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // 비밀번호만 변경 왠지 모르겠는데, 그냥 비번만
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
