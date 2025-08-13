package org.example.calendardevelop.login.service;

import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.entity.UserEntity;
import org.example.calendardevelop.login.dto.LoginRequest;
import org.example.calendardevelop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository; // 유저의 이메일과 비밀번호가 맞고, 있는지를 확인하기 위해 유저리포지토리를 가져온다.
    private final PasswordEncoder passwordEncoder;

    public Long login(LoginRequest loginRequest) { // 사용자에게 이메일과 비밀번호를 받는다.
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));

        // 입력된 비밀번호와 저장된 암호화된 비밀번호가 일치하는지 확인한다.
        if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호가 일치하면 유저 id를 반환한다.
        return userEntity.getId();
    }
}
