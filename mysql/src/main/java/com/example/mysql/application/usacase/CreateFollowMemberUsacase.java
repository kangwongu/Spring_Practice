package com.example.mysql.application.usacase;

import com.example.mysql.domain.follow.service.FollowWriteService;
import com.example.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
    Member 객체를 Follow 도메인으로 전달하는 역할
    가능한, 비즈니스 로직이 없어야 함
 */
@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsacase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    // 저장
    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            1. 입력받은 id로 회원조회
            2. FollowWriteService.create() 호출
         */
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
