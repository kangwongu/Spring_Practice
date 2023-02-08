package com.example.memo.controller;

import com.example.memo.common.CustomResponseBody;
import com.example.memo.dto.request.MemoCreateRequestDto;
import com.example.memo.dto.request.MemoUpdateRequestDto;
import com.example.memo.dto.response.MemoCreateResponseDto;
import com.example.memo.exception.StatusCode;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(new CustomResponseBody(memoService.createMemo(requestDto), StatusCode.OK), HttpStatus.OK);
    }

    // 메모 조회
    @GetMapping("/api/memos")
    public ResponseEntity<CustomResponseBody> getMemos() {
        return new ResponseEntity<>(new CustomResponseBody(memoService.getMemos(), StatusCode.OK), HttpStatus.OK);
    }

    // 메모 변경
    @PutMapping("/api/memos/{id}")
    public ResponseEntity<CustomResponseBody> updateMemo(@PathVariable Long id, @RequestBody MemoUpdateRequestDto requestDto) {
        return new ResponseEntity<>(new CustomResponseBody(memoService.updateMemo(id, requestDto), StatusCode.OK), HttpStatus.OK);
    }


    // 메모 삭제


}
