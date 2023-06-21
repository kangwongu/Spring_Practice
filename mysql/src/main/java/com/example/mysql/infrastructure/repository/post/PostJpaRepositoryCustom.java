package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Post;

import java.util.List;

public interface PostJpaRepositoryCustom {
    List<Post> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size);

    List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Long size);

    List<Post> findAllByLessThanIdAndInMemberIdOrderByIdDesc(Long key, List<Long> memberIds, Long size);

    List<Post> findAllByInMemberIdOrderByIdDesc(List<Long> memberIds, Long size);
}
