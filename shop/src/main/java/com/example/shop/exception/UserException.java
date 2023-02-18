package com.example.shop.exception;

import com.example.shop.exception.status.UserStatus;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final UserStatus status;

    public UserException(UserStatus status) {
        super(status.getStatusMsg());
        this.status = status;
    }
}
