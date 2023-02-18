package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.dto.ProductDto;
import com.example.shop.exception.ProductException;
import com.example.shop.exception.status.ProductStatus;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 관심 상품 등록
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

    // 관심 상품 조회
    @Transactional(readOnly = true)
    public List<ProductDto.ReadResponse> getProducts() {
        List<Product> find = productRepository.findAll();
        List<ProductDto.ReadResponse> response = new ArrayList<>();
        // Entity -> Dto
        for (Product product : find) {
            response.add(ProductDto.ReadResponse.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .link(product.getLink())
                    .image(product.getImage())
                    .lprice(product.getLprice())
                    .myPrice(product.getMyPrice())
                    .build());
        }
        return response;
    }

    public ProductDto.UpdateResponse updateProduct(Long id, ProductDto.UpdateRequest requestDto) {
        Product findProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductException(ProductStatus.NOT_EXIST_PRODUCT)
        );

        findProduct.updateMyPrice(requestDto.getMyPrice());
        return ProductDto.UpdateResponse.builder()
                .id(findProduct.getId()).build();
    }
}
