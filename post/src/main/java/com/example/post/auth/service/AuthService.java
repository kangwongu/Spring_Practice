package com.example.post.auth.service;

import com.example.post.auth.dto.AuthDto;
import com.example.post.exception.MemberException;
import com.example.post.exception.status.MemberStatus;
import com.example.post.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.post.exception.status.MemberStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(AuthDto.Register requestDto) {
        // 유효성 검사
        if (authRepository.findByEmail(requestDto.getEmail()).isPresent())  {
            throw new MemberException(DUPLICATED_MEMBER);
        }
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.createMember(requestDto.getEmail(), encodePassword, requestDto.getUsername()
                , requestDto.getAge());

        // 저장
        authRepository.registerMember(member);
    }

    public void login(AuthDto.Login requestDto) {
        Member findMember = authRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new MemberException(NOT_EXIST_MEMBER));

        if (!passwordEncoder.matches(requestDto.getPassword(), findMember.getPassword())) {
            throw new MemberException(NOT_CORRECT_PASSWORD);
        }


    }
}
