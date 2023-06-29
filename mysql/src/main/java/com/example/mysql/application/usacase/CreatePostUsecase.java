package com.example.mysql.application.usacase;

import com.example.mysql.domain.follow.entity.Follow;
import com.example.mysql.domain.follow.service.FollowReadService;
import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.service.PostWriteService;
import com.example.mysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatePostUsecase {

    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(PostDto.CreateRequest request) {
        Long postId = postWriteService.create(request);

        List<Long> followerMemberIds = followReadService.getFollowers(request.getMemberId())
                .stream()
                .map(follow -> follow.getFromMemberId())
                .collect(Collectors.toList());

        timelineWriteService.deliveryToTimeLine(postId, followerMemberIds);
        return postId;
    }

}
