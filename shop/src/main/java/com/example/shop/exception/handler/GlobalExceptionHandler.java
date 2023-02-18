package com.example.shop.exception.handler;

import com.example.shop.exception.ProductException;
import com.example.shop.exception.response.ExceptionResponse;
import com.example.shop.exception.status.ProductStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


//    {
//        "statusCode": "10",
//        "statusMsg": "해당 상품이 존재하지 않습니다."
//    }
    @ExceptionHandler({ProductException.class})
    public ResponseEntity<Object> productException(ProductException e) {
        ProductStatus status = e.getStatus();
        ExceptionResponse response = new ExceptionResponse(status.getStatusCode(), status.getStatusMsg());
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }
}
