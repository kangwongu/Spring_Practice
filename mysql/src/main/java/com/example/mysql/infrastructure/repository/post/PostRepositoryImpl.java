package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public List<PostDto.DailyPostCountResponse> obtainGroupByCreatedDate(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        return postJpaRepository.groupByCreatedDate(memberId, firstDate, lastDate);
    }

    @Override
    public Page<Post> findAllByMemberId(Long memberId, Pageable pageable) {
        return postJpaRepository.findAllByMemberId(memberId, pageable);
    }

    @Override
    public List<Post> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size) {
        return postJpaRepository.findAllByLessThanIdAndMemberIdOrderByIdDesc(key, memberId, size);
    }

    @Override
    public List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Long size) {
        return postJpaRepository.findAllByMemberIdOrderByIdDesc(memberId, size);
    }
}
