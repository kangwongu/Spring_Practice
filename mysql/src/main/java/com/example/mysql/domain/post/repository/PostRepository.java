package com.example.mysql.domain.post.repository;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository {

    List<PostDto.DailyPostCountResponse> obtainGroupByCreatedDate(Long memberId, LocalDate firstDate, LocalDate lastDate);

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    List<Post> findAllByLessThanIdAndMemberIdOrderByIdDesc(Long key, Long memberId, Long size);

    List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Long size);

    List<Post> findAllByLessThanIdAndInMemberIdOrderByIdDesc(Long key, List<Long> memberIds, Long size);

    List<Post> findAllByInMemberIdOrderByIdDesc(List<Long> memberIds, Long size);

    List<Post> findAllByIds(List<Long> ids);
}
