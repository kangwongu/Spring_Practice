package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.dto.ProductDto;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto.RegisterResponse registerProduct(ProductDto.RegisterRequest requestDto) {
        Product product = Product.builder()
                .title(requestDto.getTitle())
                .image(requestDto.getImage())
                .link(requestDto.getLink())
                .lprice(requestDto.getLprice())
                .build();

        Product saveProduct = productRepository.save(product);

        return ProductDto.RegisterResponse.builder()
                .id(saveProduct.getId())
                .title(saveProduct.getTitle())
                .link(saveProduct.getLink())
                .image(saveProduct.getImage())
                .lprice(saveProduct.getLprice())
                .myPrice(saveProduct.getMyPrice())
                .build();
    }
}
