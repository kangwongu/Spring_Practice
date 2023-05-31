package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    // 일자별 조회
    public List<PostDto.DailyPostCountResponse> getDailyPostCount(PostDto.DailyPostCountRequest request) {
        /*
            반환 -> [작성일자, 작성회원, 작성 게시물 개수]
         */
        return postRepository.groupByCreatedDate(request.getMemberId(), request.getFirstDate(), request.getLastDate());
    }

}
