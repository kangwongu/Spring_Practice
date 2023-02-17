package com.example.shop.exception;

import com.example.shop.exception.status.ProductStatus;
import lombok.Getter;


@Getter
public class ProductException extends RuntimeException {
    private final ProductStatus status;

    public ProductException(ProductStatus status) {
        super(status.getStatusMsg());
        this.status = status;
    }
}
