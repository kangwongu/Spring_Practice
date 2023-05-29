package com.example.mysql.application.controller;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.service.MemberReadService;
import com.example.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    // 저장
    @PostMapping()
    public MemberDto.Response register(@RequestBody MemberDto.RegisterRequest requestDto) {
        Member member = memberWriteService.register(requestDto);
        return memberReadService.toDto(member);
    }

    // 조회
    @GetMapping("/{memberId}")
    public MemberDto.Response getMember(@PathVariable Long memberId) {
        return memberReadService.getMember(memberId);
    }

    // 수정
    @PostMapping("/{memberId}/name")
    public MemberDto.Response changeNickname(@PathVariable Long memberId, @RequestBody String nickname) {
        memberWriteService.changeNickname(memberId, nickname);
        return memberReadService.getMember(memberId);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto.Res> getNicknameHistories(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
