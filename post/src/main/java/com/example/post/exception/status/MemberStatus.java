package com.example.post.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {

    DUPLICATED_MEMBER(HttpStatus.BAD_REQUEST, "중복된 회원이 존재합니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "해당 회원이 존재하지 않습니다."),
    NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
