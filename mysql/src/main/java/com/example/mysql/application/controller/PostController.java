package com.example.mysql.application.controller;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.service.PostReadService;
import com.example.mysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostReadService postReadService;
    private final PostWriteService postWriteService;

    // 등록
    @PostMapping("")
    public Long create(@RequestBody PostDto.CreateRequest request) {
        return postWriteService.create(request);
    }

    // 일자별 작성 수 조회
    @GetMapping("/daily-post-counts")
    public List<PostDto.DailyPostCountResponse> getDailyPostCounts(@ModelAttribute PostDto.DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }
}
