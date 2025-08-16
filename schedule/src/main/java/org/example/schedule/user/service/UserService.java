package org.example.schedule.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.user.dto.UserRequest;
import org.example.schedule.user.dto.UserResponse;
import org.example.schedule.user.entity.UserEntity;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Transactional // create, 회원가입
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new IllegalStateException("해당 이메일은 이미 사용 중입니다.");
        }
        UserEntity userEntity = new UserEntity(userRequest.getUserName(), userRequest.getEmail(), userRequest.getPassword());
        UserEntity saveUserEntity = userRepository.save(userEntity);
        return new UserResponse(
                saveUserEntity.getId(),
                saveUserEntity.getUserName(),
                saveUserEntity.getEmail(),
                saveUserEntity.getCreatedDate(),
                saveUserEntity.getLastModifiedDate()
        );
    }
    @Transactional
    public UserResponse getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getCreatedDate(),
                userEntity.getLastModifiedDate()
        );
    }
    @Transactional
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for(UserEntity userEntity : userEntities){
            UserResponse userResponse = new UserResponse(
                    userEntity.getId(),
                    userEntity.getUserName(),
                    userEntity.getEmail(),
                    userEntity.getCreatedDate(),
                    userEntity.getLastModifiedDate()
            );
            userResponses.add(userResponse);
        }
        return userResponses;
    }
    @Transactional
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        userEntity.userUpdate(userRequest.getUserName(), userRequest.getEmail(), userRequest.getPassword());
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getCreatedDate(),
                userEntity.getLastModifiedDate()
        );
    }
    @Transactional
    public void deleteUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 id는 존재하지 않습니다."));
        userRepository.delete(userEntity);
    }
}
