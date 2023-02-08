package com.example.memo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// 상태 코드를 enum으로 관리
@Getter
public enum StatusCode {

    OK(HttpStatus.OK, "0", "정상"),

    WRITE_MEMO_ERROR(HttpStatus.BAD_REQUEST, "100", "내용은 한글자 이상 입력해주세요.");



    // (상태코드, 임의로 정한 코드, 메세지) 형태
    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String statusMsg;

    StatusCode(HttpStatus httpStatus, String statusCode, String statusMsg) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
