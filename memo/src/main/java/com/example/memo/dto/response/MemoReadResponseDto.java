package com.example.memo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoReadResponseDto {
    private String username;
    private String contents;

    @Builder
    public MemoReadResponseDto(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }
}
