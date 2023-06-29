package com.example.mysql.application.controller;

import com.example.mysql.application.usacase.CreatePostUsecase;
import com.example.mysql.application.usacase.GetTimelinePostsUsecase;
import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.service.PostReadService;
import com.example.mysql.domain.post.service.PostWriteService;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostReadService postReadService;
    private final PostWriteService postWriteService;
    private final GetTimelinePostsUsecase getTimelinePostsUsecase;
    private final CreatePostUsecase createPostUsecase;

    // 등록
    @PostMapping("")
    public Long create(@RequestBody PostDto.CreateRequest request) {
        return createPostUsecase.execute(request);
    }

    // 일자별 작성 수 조회
    @GetMapping("/daily-post-counts")
    public List<PostDto.DailyPostCountResponse> getDailyPostCounts(@ModelAttribute PostDto.DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    // 일자별 게시글 조회 (페이지네이션, 오프셋 기반)
    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts(@PathVariable Long memberId,
                                @RequestParam Integer page,
                                @RequestParam Integer size) {
        return postReadService.getPosts(memberId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }

    // 일자별 게시글 조회 (페이지네이션, 커서 기반)
    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(@PathVariable Long memberId,
                                             @ModelAttribute CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(@PathVariable Long memberId,
                                             @ModelAttribute CursorRequest cursorRequest) {
        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
    }
}
