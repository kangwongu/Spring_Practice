package com.example.post.exception;

import com.example.post.exception.status.PostStatus;
import lombok.Getter;

@Getter
public class PostException extends RuntimeException {
    private final PostStatus status;

    public PostException(PostStatus status) {
        super(status.getErrorMessage());
        this.status = status;
    }
}
