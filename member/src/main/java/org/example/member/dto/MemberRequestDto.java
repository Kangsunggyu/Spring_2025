package org.example.member.dto;

import lombok.Getter;

// requestDto에 담긴 값을 DB에 저장하는데 활용하기에 ID 없다.
@Getter
public class MemberRequestDto {
    private String name;
    private String email;
}
