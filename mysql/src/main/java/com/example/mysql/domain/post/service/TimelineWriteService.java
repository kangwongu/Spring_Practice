package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.entity.Timeline;
import com.example.mysql.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {

    private final TimelineRepository timelineRepository;

    // 특정 유저가 게시글을 작성했을 때, 이 유저를 팔로잉하고 있는 사람들에게 게시글 배달
    public void deliveryToTimeLine(Long postId, List<Long> toMemberIds) {
        List<Timeline> timelines = toMemberIds.stream()
                .map(memberId -> toTimeline(postId, memberId))
                .collect(Collectors.toList());

        timelineRepository.saveAll(timelines);
    }

    private Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder().memberId(memberId).postId(postId).build();
    }
}
