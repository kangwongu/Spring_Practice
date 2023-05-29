package com.example.mysql.domain.follow.service;

import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.repository.FollowRepository;
import com.example.mysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    public void create(MemberDto.Response fromMember, MemberDto.Response toMember) {
        // 유효성 검사
        Assert.isTrue(!fromMember.getId().equals(toMember.getId()), "From, To 회원이 동일합니다.");

        Follow follow = Follow.builder()
                .fromMemberId(fromMember.getId())
                .toMemberId(toMember.getId())
                .build();
        followRepository.save(follow);
    }
}
