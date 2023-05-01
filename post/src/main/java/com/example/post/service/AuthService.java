package com.example.post.service;

import com.example.post.domain.Member;
import com.example.post.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthRepository authRepository;

    public void register(RegisterDto requestDto) {
        // 유효성 검사

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .username(requestDto.getUsername())
                .age(requestDto.getAge())
                .build();

        // 연관관계 세팅

        // 저장
        authRepository.registerMember(member);
    }
}
