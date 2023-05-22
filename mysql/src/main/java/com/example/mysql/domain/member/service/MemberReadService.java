package com.example.mysql.domain.member.service;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.entity.MemberNicknameHistory;
import com.example.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    // 조회
    public MemberDto.Response getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        return toDto(member);
    }

    public List<MemberNicknameHistoryDto.Res> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(history -> toDto(history))
                .collect(Collectors.toList());
    }

    public MemberDto.Response toDto(Member member) {
        return new MemberDto.Response(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    private MemberNicknameHistoryDto.Res toDto(MemberNicknameHistory history) {
        return MemberNicknameHistoryDto.Res.builder()
                .id(history.getId())
                .memberId(history.getMemberId())
                .nickname(history.getNickname())
                .createAt(history.getCreatedAt())
                .build();
    }
}
