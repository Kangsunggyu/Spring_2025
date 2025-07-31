package org.example.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.member.dto.MemberRequestDto;
import org.example.member.dto.MemberResponseDto;
import org.example.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("members")
    public MemberResponseDto createMember(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.createMember(memberRequestDto);
    }
    @GetMapping("members")
    public List<MemberResponseDto> readAllMember(){
        return memberService.readAllMember();
    }
    @GetMapping("members/{memberId}")
    public MemberResponseDto readMember(@PathVariable("memberId") Long memberId){
        return memberService.readMember(memberId);
    }
    @PutMapping("members/{memberId}")
    public MemberResponseDto updateMember(
            @PathVariable("memberId") Long memberId,
            @RequestBody MemberRequestDto memberRequestDto){
        return memberService.updateMember(memberId, memberRequestDto);
    }
    @DeleteMapping("members/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long memberId){
        memberService.deleteMember(memberId);
    }
}
