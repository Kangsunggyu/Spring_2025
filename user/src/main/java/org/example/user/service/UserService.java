package org.example.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.user.dto.UserResponseDto;
import org.example.user.dto.UserRequestDto;
import org.example.user.entity.UserEntity;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity(userRequestDto.getUsername(), userRequestDto.getPassword());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return new UserResponseDto (
                savedUserEntity.getUsername(),
                savedUserEntity.getPassword()
        );
    }
    // 유저 이름을 찾음
    @Transactional
    public UserResponseDto readByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + username));
        return new UserResponseDto (userEntity.getUsername(), userEntity.getPassword());
    }

    @Transactional
    public UserResponseDto updateMember(String username, UserRequestDto userRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: "));
        userEntity.updatePassword(userRequestDto.getPassword());
        return new UserResponseDto(userEntity.getUsername(), userEntity.getPassword());
    }
    // delete 메서드
    @Transactional
    public void deleteMember(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: "));
        userRepository.delete(userEntity);
    }
}
