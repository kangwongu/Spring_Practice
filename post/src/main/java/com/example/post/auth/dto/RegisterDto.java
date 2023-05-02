package com.example.post.auth.dto;

import lombok.Getter;

@Getter
public class RegisterDto {
    private String email;
    private String password;
    private String username;
    private Integer age;
}
