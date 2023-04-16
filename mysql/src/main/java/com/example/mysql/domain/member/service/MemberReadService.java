package com.example.mysql.domain.member.service;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberRepository memberRepository;

    // 조회
    public MemberDto.Response getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        return toDto(member);
    }

    public MemberDto.Response toDto(Member member) {
        return new MemberDto.Response(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
