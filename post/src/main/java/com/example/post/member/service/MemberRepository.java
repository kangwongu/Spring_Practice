package com.example.post.member.service;

import com.example.post.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByEmail(String email);
}
