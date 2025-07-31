package org.example.member.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.member.dto.MemberRequestDto;
import org.example.member.dto.MemberResponseDto;
import org.example.member.entity.MemberEntity;
import org.example.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 자동 생성
public class MemberService {
    private final MemberRepository memberRepository; // MemberRepository 객체를 사용하겠다고 선언

    // Create 메서드
    @Transactional
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        // 1. memberRequestDto에서 핗요한 데이터를 추출해, 새로운 Member(엔티티) 객체를 생성한다.
        MemberEntity member = new MemberEntity(memberRequestDto.getName(), memberRequestDto.getEmail());

        // 2. 그렇게 만들어지 객체를 MemberEntity에 저장한다.
        MemberEntity savedMember = memberRepository.save(member);

        // 3. 저장된 memberEntity 객체를 통해 ResponseDto를 반환한다.
        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getEmail()
        );
    }
    //Read 메서드
    @Transactional
    public MemberResponseDto readMember(Long id) {
        MemberEntity member = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("id가 없습니다."));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail());
    }
    // 전체를 보여주는 메서드
    @Transactional
    public List<MemberResponseDto> readAllMember() {
        List<MemberEntity> list = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (MemberEntity member : list) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(
                    member.getId(),
                    member.getName(),
                    member.getEmail());
            memberResponseDtoList.add(memberResponseDto);
        }
        return memberResponseDtoList;
    }
    // update 메서드
    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        MemberEntity member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id 없습니다."));
        member.updateName(memberRequestDto.getName(),  memberRequestDto.getEmail());
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail());
    }
    // delete 메서드
    @Transactional
    public void deleteMember(Long id) {
        MemberEntity member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id 없습니다."));
        memberRepository.deleteById(id);
    }
}
