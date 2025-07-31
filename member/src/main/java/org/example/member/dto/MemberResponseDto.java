package org.example.member.dto;

import lombok.Getter;

// 정보를 전달하기 위해 위한 변경 불가능한 데이터 묶는 객체
@Getter
public class MemberResponseDto {
    private final Long id;
    private final String name;
    private final String email;

    // 매개변수를 받는 생성자
    public MemberResponseDto(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
