package com.example.mysql.controller;

import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.entity.Member;
import com.example.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberWriteService memberWriteService;

    // 저장
    @PostMapping("/members")
    public Member register(@RequestBody MemberDto.RegisterRequest requestDto) {
        return memberWriteService.create(requestDto);
    }
}
