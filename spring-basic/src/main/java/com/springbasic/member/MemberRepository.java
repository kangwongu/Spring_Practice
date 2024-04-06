package com.springbasic.member;

// 역할과 구현 분리
// 역할
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
