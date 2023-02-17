package com.example.shop.dto;

import com.example.shop.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

    // 관심 상품 등록 Dto
    @Getter
    public static class RegisterRequest {
        private String title;
        private String image;
        private String link;
        private int lprice;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegisterResponse {
        private Long id;
        private String title;
        private String link;
        private String image;
        private int lprice;
        private int myPrice;


//        public RegisterResponse toDto(Product product) {
//            id = product.getId();
//            title = product.getTitle();
//            link = product.getLink();
//            image = product.getImage();
//            lPrice = product.getLPrice();
//            myPrice = product.getMyPrice();
//        }

        @Builder
        public RegisterResponse(Long id, String title, String link, String image, int lprice, int myPrice) {
            this.id = id;
            this.title = title;
            this.link = link;
            this.image = image;
            this.lprice = lprice;
            this.myPrice = myPrice;
        }
    }
}
