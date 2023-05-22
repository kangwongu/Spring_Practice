package com.example.mysql.domain.member.repository;


import com.example.mysql.domain.member.entity.MemberNicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long> {
    List<MemberNicknameHistory> findAllByMemberId(Long memberId);
}
