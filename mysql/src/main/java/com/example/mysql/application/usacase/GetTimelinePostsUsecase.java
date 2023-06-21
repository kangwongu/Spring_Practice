package com.example.mysql.application.usacase;


import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.service.FollowReadService;
import com.example.mysql.domain.post.entity.Post;
import com.example.mysql.domain.post.service.PostReadService;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetTimelinePostsUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    // 커서 기반 페이징으로 반환
    /*
        memberId -> follow한 사람들 조회
        조회한 follow들의 post 조회
     */
    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        List<Follow> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream()
                .map(f -> f.getToMemberId())
                .collect(Collectors.toList());
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

}
