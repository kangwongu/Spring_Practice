package com.example.shop.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

    //ex) 상품이 존재하지 않는다면
    NOT_EXIST_PRODUCT(HttpStatus.BAD_REQUEST, "10", "해당 상품이 존재하지 않습니다.");

    // (http 상태코드, 임의로 정한 코드, 메시지)
    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String statusMsg;
}
