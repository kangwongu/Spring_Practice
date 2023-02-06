package com.example.memo.exception;

import com.example.memo.common.CustomResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseBody> handleException(CustomException e) {
        // 어디서 무슨 에러가 발생했는지 로그
        // ex) 한글자 이상 입력해주세요. : com.example.memo.service.MemoService.createMemo(MemoService.java:22)
        log.error("{} : {}", e.getStatusCode().getStatusMsg(), e.getStackTrace()[0]);

        String errorCode = e.getStatusCode().getStatusCode();
        String errorMsg = e.getStatusCode().getStatusMsg();

        CustomResponseBody customResponseBody = new CustomResponseBody();
        customResponseBody.setStatusCode(errorCode);
        customResponseBody.setStatusMsg(errorMsg);

        return new ResponseEntity<>(customResponseBody, e.getStatusCode().getHttpStatus());
    }
}
