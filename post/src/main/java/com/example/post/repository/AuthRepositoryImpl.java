package com.example.post.repository;

import com.example.post.domain.Member;
import com.example.post.service.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;

    @Override
    public void registerMember(Member member) {
        authJpaRepository.save(member);
    }
}
