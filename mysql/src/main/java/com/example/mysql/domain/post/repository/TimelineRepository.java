package com.example.mysql.domain.post.repository;

import com.example.mysql.domain.post.entity.Timeline;

import java.util.List;

public interface TimelineRepository {

    // 저장
    Timeline save(Timeline timeline);

    List<Timeline> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size);

    List<Timeline> findAllByMemberIdOrderByIdDesc(Long memberId, Long size);

    void saveAll(List<Timeline> timelines);
}
