package com.example.post.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    NO_POST(HttpStatus.NO_CONTENT, "등록한 게시글이 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
