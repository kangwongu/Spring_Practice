package com.example.post.repository;

import com.example.post.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<Member, Long> {
}
