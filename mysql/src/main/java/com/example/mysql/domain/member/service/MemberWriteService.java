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
    public Member create(MemberDto.RegisterRequest requestDto) {
        Member member = Member.builder()
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .birthday(requestDto.getBirthday())
                .build();

        return memberRepository.save(member);
    }
}
