package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.infrastructure.repository.post.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteService {

    private final PostJpaRepository postRepository;

    // 등록
    public Long create(PostDto.CreateRequest request) {
        Post post = Post.builder()
                .memberId(request.getMemberId())
                .contents(request.getContents())
                .build();

        return postRepository.save(post).getId();
    }

}
