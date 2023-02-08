package com.example.memo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoCreateResponseDto {
    private Long id;

    @Builder
    public MemoCreateResponseDto(Long id) {
        this.id = id;
    }
}
