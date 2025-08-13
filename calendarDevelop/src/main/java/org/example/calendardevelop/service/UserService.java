package org.example.calendardevelop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.UserRequest;
import org.example.calendardevelop.dto.UserResponse;
import org.example.calendardevelop.entity.UserEntity;
import org.example.calendardevelop.login.filter.PasswordEncoder;
import org.example.calendardevelop.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        UserEntity userEntity = new UserEntity(userRequest.getUserName(), userRequest.getEmail(), encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return new UserResponse(savedUserEntity);
    }


    @Transactional
    public UserResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 유저를 찾을 수 없습니다."));
        return new UserResponse(userEntity);
    }

    @Transactional
    public List<UserResponse> getAllUsers() {
        List<UserEntity> list =  userRepository.findAll();
        List<UserResponse> calendarResponseDtoList = new ArrayList<>();
        for (UserEntity userEntity : list) {
            UserResponse userResponse = new UserResponse(userEntity);
            calendarResponseDtoList.add(userResponse);
        }
        return calendarResponseDtoList;
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 유저를 찾을 수 없습니다."));
        userEntity.updateUser(userRequest.getUserName(), userRequest.getEmail(), userRequest.getPassword());
        return new UserResponse(userEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "번 유저를 찾을 수 없습니다."));
        userRepository.delete(userEntity);
    }
}
