package com.example.post.auth.repository;

import com.example.post.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
