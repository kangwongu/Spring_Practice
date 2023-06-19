package com.example.mysql.application.controller;

import com.example.mysql.application.usacase.CreateFollowMemberUsecase;
import com.example.mysql.application.usacase.GetFollowingMembersUsecase;
import com.example.mysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final CreateFollowMemberUsecase createFollowMemberUsecase;
    private final GetFollowingMembersUsecase getFollowingMembersUsecase;

    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto.Response> register(@PathVariable Long fromId) {
        return getFollowingMembersUsecase.execute(fromId);
    }
}
