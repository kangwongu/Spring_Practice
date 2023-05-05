package com.example.post.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class PostDto {

    @Getter
    public static class ListResponse {
        private String title;
        private String author;
        private LocalDateTime createTime;

        public ListResponse(String title, String author, LocalDateTime createTime) {
            this.title = title;
            this.author = author;
            this.createTime = createTime;
        }
    }
}
