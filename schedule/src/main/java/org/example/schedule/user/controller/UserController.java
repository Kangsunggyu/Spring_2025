package org.example.schedule.user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedule.user.dto.UserRequest;
import org.example.schedule.user.dto.UserResponse;
import org.example.schedule.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, HttpSession session){
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        return ResponseEntity.ok(userService.updateUser(userId,userRequest));
    }

    @DeleteMapping("/users/me")
    public void deleteUser(HttpSession session){
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        if (userId != null) {
            userService.deleteUserById(userId);
        }
        session.invalidate();
    }
}


/*
@PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userId,userRequest));
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }
 */
