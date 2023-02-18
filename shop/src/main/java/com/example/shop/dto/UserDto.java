package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter @Setter
    public static class SignUpRequest {
        private String username;
        private String password;
        private String email;
        private boolean admin = false;
        private String adminToken = "";
    }

    @Getter @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }
}
