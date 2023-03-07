package com.example.shop.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FolderStatus {

    NOT_EXIST_FOLDER(HttpStatus.BAD_REQUEST, "30", "해당 폴더가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String statusMsg;
}
