package com.example.post.member.domain;

import com.example.post.common.timestamp.Timestamp;
import com.example.post.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;

    private Integer age;

    private String email;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String username, Integer age, String email, String password) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String email, String password, String username, Integer age) {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .age(age)
                .build();
        return member;
    }
}
