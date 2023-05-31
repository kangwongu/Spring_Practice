package com.example.mysql.domain.post.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class PostDto {

    @Data
    public static class CreateRequest {
        private Long memberId;
        private String contents;
    }

    @Data
    public static class DailyPostCountRequest {
        private Long memberId;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate firstDate;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate lastDate;
    }

    @Data
    public static class DailyPostCountResponse {
        private Long memberId;
        private LocalDate date;
        private Long postCount;

        public DailyPostCountResponse(Long memberId, LocalDate date, Long postCount) {
            this.memberId = memberId;
            this.date = date;
            this.postCount = postCount;
        }
    }
}
