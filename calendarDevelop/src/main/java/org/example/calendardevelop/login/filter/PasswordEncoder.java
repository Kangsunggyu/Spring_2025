package org.example.calendardevelop.login.filter;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    // 비밀번호를 암호화하는 메서드
    public String encode(String rawPassword) {
        // rawPassword(평문)를 해싱하여 String 형태로 반환
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교하는 메서드
    public boolean matches(String rawPassword, String encodedPassword) {
        // rawPassword(평문)와 encodedPassword(암호화된 비밀번호)를 비교
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}