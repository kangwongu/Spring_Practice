package com.example.mysql.domain.post.service;

import com.example.mysql.domain.post.entity.Timeline;
import com.example.mysql.domain.post.repository.TimelineRepository;
import com.example.mysql.util.CursorRequest;
import com.example.mysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineReadService {

    private final TimelineRepository timelineRepository;

    // 특정 회원의 타임라인 조회
    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
        List<Timeline> timelines = findAllBy(memberId, cursorRequest);
        long nextKey = timelines.stream()
                .mapToLong(t -> t.getId())
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(nextKey), timelines);
    }

    private List<Timeline> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineRepository.findAllByLessThanIdAndMemberIdOrderByIdDesc(cursorRequest.getKey(), memberId, cursorRequest.getSize());
        } else {
            return timelineRepository.findAllByMemberIdOrderByIdDesc(memberId, cursorRequest.getSize());
        }
    }
}
