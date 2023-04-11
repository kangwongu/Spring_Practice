package com.example.mysql.domain.member.dto;

import lombok.Getter;

import java.time.LocalDate;

public class MemberDto {

    @Getter
    public static class RegisterRequest {
        private String email;
        private String nickname;
        private LocalDate birthday;
    }
}
