package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.entity.Timeline;
import com.example.mysql.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimelineRepositoryImpl implements TimelineRepository {

    private final TimelineJpaRepository timelineJpaRepository;
    private final TimelineJpaRepositoryCustom timelineJpaRepositoryCustom;
    private final TimelineBulkRepository timelineBulkRepository;

    @Override
    public Timeline save(Timeline timeline) {
        return timelineJpaRepository.save(timeline);
    }

    @Override
    public List<Timeline> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size) {
        return timelineJpaRepositoryCustom.findAllByLessThanIdAndMemberIdOrderByIdDesc(key, memberId, size);
    }

    @Override
    public List<Timeline> findAllByMemberIdOrderByIdDesc(Long memberId, Long size) {
        return timelineJpaRepositoryCustom.findAllByMemberIdOrderByIdDesc(memberId, size);
    }

    @Override
    public void saveAll(List<Timeline> timelines) {
        timelineBulkRepository.saveAllByBulk(timelines);
    }

}
