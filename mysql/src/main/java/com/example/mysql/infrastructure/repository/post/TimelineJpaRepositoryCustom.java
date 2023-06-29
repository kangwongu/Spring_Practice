package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.entity.Timeline;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mysql.domain.post.entity.QTimeline.timeline;

@Repository
@RequiredArgsConstructor
public class TimelineJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<Timeline> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size) {
        return queryFactory
                .selectFrom(timeline)
                .where(
                        timeline.memberId.eq(memberId),
                        timeline.id.lt(key)
                )
                .orderBy(timeline.id.desc())
                .limit(size)
                .fetch();
    }

    public List<Timeline> findAllByMemberIdOrderByIdDesc(Long memberId, Long size) {
        return queryFactory
                .selectFrom(timeline)
                .where(
                        timeline.memberId.eq(memberId)
                )
                .orderBy(timeline.id.desc())
                .limit(size)
                .fetch();
    }
}
