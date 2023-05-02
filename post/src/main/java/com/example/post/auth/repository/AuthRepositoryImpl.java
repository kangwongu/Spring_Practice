package com.example.post.auth.repository;

import com.example.post.member.domain.Member;
import com.example.post.auth.service.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;

    @Override
    public void registerMember(Member member) {
        authJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return authJpaRepository.findByEmail(email);
    }
}
