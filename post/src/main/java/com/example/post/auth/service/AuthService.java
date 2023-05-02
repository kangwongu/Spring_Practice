package com.example.post.auth.service;

import com.example.post.exception.MemberException;
import com.example.post.exception.status.MemberStatus;
import com.example.post.member.domain.Member;
import com.example.post.auth.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthRepository authRepository;

    public void register(RegisterDto requestDto) {
        // 유효성 검사
        if (authRepository.findByEmail(requestDto.getEmail()).isPresent())  {
            throw new MemberException(MemberStatus.DUPLICATED_MEMBER);
        }

        Member member = Member.createMember(requestDto.getEmail(), requestDto.getPassword(), requestDto.getUsername()
                , requestDto.getAge());

        // 저장
        authRepository.registerMember(member);
    }
}
