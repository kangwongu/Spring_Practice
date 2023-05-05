package com.example.post.post.service;

import com.example.post.post.domain.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAllPost();
}
