package org.example.calendardevelop.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.dto.UserRequest;
import org.example.calendardevelop.dto.UserResponse;
import org.example.calendardevelop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signIn") // 회원가입하는 기능
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping // 모든 회원을 보여주는 기능
    public List<UserResponse> readAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}") // /users/1하면 1번 사용자의 정보를 보여주는 기능
    public UserResponse readUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}") // 업데이트
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}") // 삭제
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
