package com.example.post.domain;

import com.example.post.domain.timestamp.Timestamp;
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
    private Long userId;

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
}
