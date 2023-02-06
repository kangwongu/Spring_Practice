package com.example.memo.exception;

import lombok.Getter;

/**
 * {
 * "object": null,
 * "statusCode": "100",
 * "statusMsg": "한글자 이상 입력해주세요."
 * }
 */

// 사용자 예외
@Getter
public class CustomException extends RuntimeException {
    // 에러 발생 시, StatusCode enum을 반환
    private StatusCode statusCode;

    public CustomException(StatusCode statusCode) {
        super(statusCode.getStatusMsg());
        this.statusCode = statusCode;
    }
}
