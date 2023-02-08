package com.example.memo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoUpdateResponseDto {
    private Long id;

    @Builder
    public MemoUpdateResponseDto(Long id) {
        this.id = id;
    }
}
