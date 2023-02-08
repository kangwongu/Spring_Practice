package com.example.memo.domain;

import com.example.memo.domain.timestamped.Timestamped;
import com.example.memo.dto.request.MemoUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @Builder
    public Memo(String username, String content) {
        this.username = username;
        this.content = content;
    }

    //==비즈니스 메소드==//
    // 메모 수정
    public void updateMemo(MemoUpdateRequestDto requestDto) {
        username = requestDto.getUsername();
        content = requestDto.getContents();
    }
}
