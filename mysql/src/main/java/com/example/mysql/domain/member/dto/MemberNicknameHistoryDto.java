package com.example.mysql.domain.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberNicknameHistoryDto {

    @Data
    public static class Res {
        private Long id;
        private Long memberId;
        private String nickname;
        private LocalDateTime createAt;

        @Builder
        public Res(Long id, Long memberId, String nickname, LocalDateTime createAt) {
            this.id = id;
            this.memberId = memberId;
            this.nickname = nickname;
            this.createAt = createAt;
        }
    }
}
