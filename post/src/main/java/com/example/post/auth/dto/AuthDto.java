package com.example.post.auth.dto;

import lombok.Getter;

public class AuthDto {

    @Getter
    public static class Register {
        private String email;
        private String password;
        private String username;
        private Integer age;
    }

    @Getter
    public static class Login {
        private String email;
        private String password;
    }

}
