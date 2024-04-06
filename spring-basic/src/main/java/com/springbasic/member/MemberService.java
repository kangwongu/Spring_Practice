package com.springbasic.member;

// 제공할 기능
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
