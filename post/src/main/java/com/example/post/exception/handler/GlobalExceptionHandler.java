package com.example.post.exception.handler;

import com.example.post.exception.MemberException;
import com.example.post.exception.response.ExceptionResponse;
import com.example.post.exception.status.MemberStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MemberException.class})
    public ResponseEntity<Object> memberException(MemberException e) {
        MemberStatus status = e.getStatus();
        ExceptionResponse response = new ExceptionResponse(status.getHttpStatus(), status.getErrorMessage());
        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }
}
