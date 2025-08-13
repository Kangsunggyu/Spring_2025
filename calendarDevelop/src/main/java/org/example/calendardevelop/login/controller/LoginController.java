package org.example.calendardevelop.login.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.calendardevelop.entity.UserEntity;
import org.example.calendardevelop.login.dto.LoginRequest;
import org.example.calendardevelop.login.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login") // /auth/login 하면 로그인 하는 기능
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            // 로그인 성공 시 세션에 사용자 ID를 저장하고, 클라이언트에게 JSESSIONID 쿠키를 발급
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", loginService.login(loginRequest)); // 유저 고유 id를 저장
            return ResponseEntity.ok("로그인 성공");
        } catch (IllegalArgumentException e) {
            // 이메일이나 비밀번호가 일치하지 않을 경우 401 Unauthorized 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: " + e.getMessage());
        }
    }
    @PostMapping("/logout") // auth/logout하면 로그아웃 하는 기능
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
            return ResponseEntity.ok("로그아웃 성공");
        }
        return ResponseEntity.ok("이미 로그아웃되었습니다.");
    }
}
