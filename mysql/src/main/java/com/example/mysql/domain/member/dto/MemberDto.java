package com.example.mysql.domain.member.dto;

import com.example.mysql.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MemberDto {

    @Getter
    public static class RegisterRequest {
        private String email;
        private String nickname;
        private LocalDate birthday;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String nickname;
        private LocalDate birthday;
    }
}
