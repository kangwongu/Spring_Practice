package com.example.post.exception.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final HttpStatus statusMessage;
    private final String errorMessage;
}
