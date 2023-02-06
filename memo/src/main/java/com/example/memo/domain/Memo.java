package com.example.memo.domain;

import com.example.memo.domain.timestamped.Timestamped;
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
}
