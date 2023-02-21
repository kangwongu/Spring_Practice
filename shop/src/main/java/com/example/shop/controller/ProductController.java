package com.example.shop.controller;

import com.example.shop.dto.ProductDto;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 등록
    @PostMapping("/products")
    public ResponseEntity<ProductDto.RegisterResponse> registerProduct(@RequestBody ProductDto.RegisterRequest requestDto) {
        ProductDto.RegisterResponse response = productService.registerProduct(requestDto);
        return ResponseEntity.ok().body(response);
    }

    // 관심 상품 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto.ReadResponse>> getProducts(HttpServletRequest request) {
        List<ProductDto.ReadResponse> response = productService.getProducts(request);
        return ResponseEntity.ok().body(response);
    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto.UpdateResponse> updateProduct(@PathVariable Long id,
                                                                   @RequestBody ProductDto.UpdateRequest requestDto) {
        ProductDto.UpdateResponse response = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok().body(response);
    }
}
