package com.example.shop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myPrice;

    @Builder
    public Product(Long id, String title, String image, String link, int lprice, int myPrice) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
        this.myPrice = myPrice;
    }
}
