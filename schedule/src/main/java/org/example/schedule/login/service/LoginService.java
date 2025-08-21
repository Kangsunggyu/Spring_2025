package org.example.schedule.login.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.login.dto.LoginRequest;
import org.example.schedule.user.entity.UserEntity;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    @Transactional // 해당 유저의 id를 리턴한다.
    public Long login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지 않습니다."));
        if(userEntity.getPassword().equals(loginRequest.getPassword())){
            return userEntity.getId();
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
