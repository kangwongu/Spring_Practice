package com.example.post.post.controller;

import com.example.post.post.dto.PostDto;
import com.example.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<PostDto.ListResponse>> obtainAllPost() {
        List<PostDto.ListResponse> response = postService.obtainAllPost();
        return ResponseEntity.ok().body(response);
    }

    // 등록

    // 특정 조회

    // 수정

    // 삭제

}
