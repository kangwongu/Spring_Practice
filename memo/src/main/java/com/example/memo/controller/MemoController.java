package com.example.memo.controller;

import com.example.memo.common.CustomResponseBody;
import com.example.memo.dto.request.MemoCreateRequestDto;
import com.example.memo.exception.StatusCode;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // 메모 페이지 띄우기
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    // 메모 생성
    @PostMapping("/api/memos")
    public ResponseEntity<CustomResponseBody> createMemo(@RequestBody MemoCreateRequestDto requestDto) {
        Long memoId = memoService.createMemo(requestDto);
        return new ResponseEntity<>(new CustomResponseBody(memoId, StatusCode.OK), HttpStatus.OK);
    }

    // 메모 조회


    // 메모 변경


    // 메모 삭제


}
