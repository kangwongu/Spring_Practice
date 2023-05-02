package com.example.post.auth.service;

import com.example.post.member.domain.Member;

import java.util.Optional;

public interface AuthRepository {

    void registerMember(Member member);

    Optional<Member> findByEmail(String email);
}
