package com.example.post.post.repository;

import com.example.post.post.domain.Post;
import com.example.post.post.service.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;

    @Override
    public List<Post> findAllPost() {
        return postJpaRepository.findAll();
    }
}
