package com.example.post.security.userdetails;

import com.example.post.exception.MemberException;
import com.example.post.member.domain.Member;
import com.example.post.member.service.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.post.exception.status.MemberStatus.NOT_EXIST_MEMBER;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(NOT_EXIST_MEMBER));
        return new UserDetailsImpl(member);
    }
}
