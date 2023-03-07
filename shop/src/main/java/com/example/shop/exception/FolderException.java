package com.example.shop.exception;

import com.example.shop.exception.status.FolderStatus;
import lombok.Getter;

@Getter
public class FolderException extends RuntimeException {
    private final FolderStatus status;

    public FolderException(FolderStatus status) {
        super(status.getStatusMsg());
        this.status = status;
    }
}
