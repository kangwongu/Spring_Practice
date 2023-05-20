package com.example.productorderservice;

import org.springframework.util.Assert;

class AddProductRequest {

    private final String productName;
    private final int price;
    private final DiscountPolicy discountPolicy;

    public AddProductRequest(final String productName, final int price, final DiscountPolicy discountPolicy) {
        Assert.hasText(productName, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        this.productName = productName;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}