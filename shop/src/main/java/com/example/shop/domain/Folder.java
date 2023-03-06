package com.example.shop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String name;

    @Builder
    public Folder(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //== 연관관계 ==//
    public void registerUser(User user) {
        if (this.user != null) {
            user.getFolders().remove(this);
        }
        this.user = user;
        user.getFolders().add(this);
    }
}
