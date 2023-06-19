package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.repository.PostRepository;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.infrastructure.repository.post.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

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
        return postRepository.obtainGroupByCreatedDate(request.getMemberId(), request.getFirstDate(), request.getLastDate());
    }

    // 일자별 게시글 조회 (페이지네이션, 오프셋 기반)
    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }

    // 일자별 게시글 조회 (페이지네이션, 커서 기반)]
    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(memberId, cursorRequest);
        long nextKey = posts.stream()
                .mapToLong(post -> post.getId())
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postRepository.findAllByLessThanIdAndMemberIdOrderByIdDesc(cursorRequest.getKey(), memberId, cursorRequest.getSize());
        } else {
            return postRepository.findAllByMemberIdOrderByIdDesc(memberId, cursorRequest.getSize());
        }
    }

//    public PageCursor<Post> getPosts(List<Long> memberIds, CursorRequest cursorRequest) {
//        List<Post> posts = findAllBy(memberId, cursorRequest);
//        long nextKey = posts.stream()
//                .mapToLong(post -> post.getId())
//                .min()
//                .orElse(CursorRequest.NONE_KEY);
//
//        return new PageCursor<>(cursorRequest.next(nextKey), posts);
//    }

}
