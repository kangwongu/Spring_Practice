package com.example.mysql.domain.member.service;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    // 저장
    public Member register(MemberDto.RegisterRequest requestDto) {
        Member member = Member.builder()
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .birthday(requestDto.getBirthday())
                .build();

        return memberRepository.save(member);
    }

    public void changeNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);

    }
}
