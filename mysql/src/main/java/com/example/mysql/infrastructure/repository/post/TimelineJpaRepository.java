package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineJpaRepository extends JpaRepository<Timeline, Long> {

    List<Timeline> findAllByMemberId(Long memberId);
}
