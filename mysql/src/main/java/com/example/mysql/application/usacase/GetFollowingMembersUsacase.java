package com.example.mysql.application.usacase;

import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.service.FollowReadService;
import com.example.mysql.domain.member.dto.MemberDto;
import com.example.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFollowingMembersUsacase {

    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    // 조회
    public List<MemberDto.Response> execute(Long memberId) {
        /*
            fromMemberId = memberId -> Follow list 조회
            조회하며 회원정보 찾아서 반환
         */
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream()
                .map(f -> f.getToMemberId())
                .collect(Collectors.toList());

        return memberReadService.getMembers(followingMemberIds);
    }

}
