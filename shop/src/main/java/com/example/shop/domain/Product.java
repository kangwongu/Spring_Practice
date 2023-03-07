package com.example.shop.domain;

import com.example.shop.dto.ProductDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private Long userId;

    @ManyToMany
    private List<Folder> folders = new ArrayList<>();

    @Builder
    public Product(Long id, String title, String image, String link, int lprice, int myPrice) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
        this.myPrice = myPrice;
    }

//    public Product(ProductDto.R requestDto, Long userId) {
//        this.title = requestDto.getTitle();
//        this.image = requestDto.getImage();
//        this.link = requestDto.getLink();
//        this.lprice = requestDto.getLprice();
//        this.myprice = 0;
//        this.userId = userId;
//    }

    //==비즈니스 메소드==//
    public void updateMyPrice(int myPrice) {
        this.myPrice = myPrice;
    }

    public void addFolder(Folder folder) {
        this.folders.add(folder);
    }
}
