package com.example.post.controller;

import com.example.post.dto.RegisterDto;
import com.example.post.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto requestDto) {
        authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인


}
