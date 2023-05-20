package com.example.productorderservice;

import org.springframework.stereotype.Service;

@Service
class ProductService {
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.getProductName(), request.getPrice(), request.getDiscountPolicy());
        productPort.save(product);
    }
}
