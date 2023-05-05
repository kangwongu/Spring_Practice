package com.example.post.post.service;


import com.example.post.post.domain.Post;
import com.example.post.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto.ListResponse> obtainAllPost() {
        List<Post> posts = postRepository.findAllPost();
        return posts.stream()
                .map(post -> post.toPostListDto())
                .collect(Collectors.toList());
    }
}
