package org.example.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.user.dto.UserRequestDto;
import org.example.user.dto.UserResponseDto;
import org.example.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        return userService.addUser(userRequestDto);
    }
    @GetMapping("/users/{userName}")
    public UserResponseDto readUser(@PathVariable("userName") String userName){
        return userService.readByUsername(userName);
    }
    @PutMapping("/users/{userName}")
    public UserResponseDto updateUser(
            @PathVariable("userName") String userName,
            @RequestBody UserRequestDto userRequestDto){
        return userService.updateMember(userName, userRequestDto);
    }
    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userService.deleteMember(userName);
    }
}
