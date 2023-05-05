package com.example.post.auth.controller;

import com.example.post.auth.dto.AuthDto;
import com.example.post.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthDto.Register requestDto) {
        authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AuthDto.Login requestDto) {
        HttpHeaders headers = authService.login(requestDto);
        return ResponseEntity.ok().headers(headers).build();
    }
}
