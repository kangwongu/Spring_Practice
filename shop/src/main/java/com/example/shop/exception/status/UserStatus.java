package com.example.shop.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum UserStatus {

    DUPLICATED_USER(HttpStatus.BAD_REQUEST, "20", "중복된 사용자가 존재합니다"),
    INVALID_ADMIN_TOKEN(HttpStatus.BAD_REQUEST, "21", "관리자 토큰이 틀립니다."),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "22", "해당 사용자가 존재하지 않습니다."),
    NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "23", "비밀번호가 일치하지 않습니다"),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "24", "JWT 토큰이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String statusMsg;
}
