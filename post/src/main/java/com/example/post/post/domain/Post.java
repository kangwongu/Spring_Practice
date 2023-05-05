package com.example.post.post.domain;

import com.example.post.common.timestamp.Timestamp;
import com.example.post.member.domain.Member;
import com.example.post.post.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PostDto.ListResponse toPostListDto() {
        return new PostDto.ListResponse(title, getAuthor(), getCreatedAt());
    }

    private String getAuthor() {
        return member.getUsername();
    }
}
