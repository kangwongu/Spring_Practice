package com.example.post.exception;

import com.example.post.exception.status.MemberStatus;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private final MemberStatus status;

    public MemberException(MemberStatus status) {
        super(status.getErrorMessage());
        this.status = status;
    }
}
