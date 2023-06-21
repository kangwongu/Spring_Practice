package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.mysql.domain.post.entity.QPost.post;

@Repository
public class PostJpaRepositoryCustomImpl implements PostJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostJpaRepositoryCustomImpl(EntityManager em) {queryFactory = new JPAQueryFactory(em); }

    @Override
    public List<Post> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size) {
        return queryFactory
                .selectFrom(post)
                .where(
                        post.memberId.eq(memberId),
                        post.id.lt(key)
                )
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }

    @Override
    public List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Long size) {
        return queryFactory
                .selectFrom(post)
                .where(
                        post.memberId.eq(memberId)
                )
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }

    @Override
    public List<Post> findAllByLessThanIdAndInMemberIdOrderByIdDesc(Long key, List<Long> memberIds, Long size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }

        return queryFactory
                .selectFrom(post)
                .where(
                        post.memberId.in(memberIds),
                        post.id.lt(key)
                )
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }

    @Override
    public List<Post> findAllByInMemberIdOrderByIdDesc(List<Long> memberIds, Long size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }

        return queryFactory
                .selectFrom(post)
                .where(
                        post.memberId.in(memberIds)
                )
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }

}
