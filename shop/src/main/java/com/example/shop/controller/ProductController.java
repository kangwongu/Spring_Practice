package com.example.shop.controller;

import com.example.shop.dto.ProductDto;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ProductDto.RegisterResponse> registerProduct(@RequestBody ProductDto.RegisterRequest requestDto,
                                                                       HttpServletRequest request) {
        ProductDto.RegisterResponse response = productService.registerProduct(requestDto, request);
        return ResponseEntity.ok().body(response);
    }

    // 관심 상품 조회
    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto.ReadResponse>> getProducts(@RequestParam int page,
                                                                     @RequestParam int size,
                                                                     @RequestParam String sortBy,
                                                                     @RequestParam boolean isAsc,
                                                                     HttpServletRequest request) {
        Page<ProductDto.ReadResponse> response = productService.getProducts(request, page-1, size, sortBy, isAsc);
        return ResponseEntity.ok().body(response);
    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto.UpdateResponse> updateProduct(@PathVariable Long id,
                                                                   @RequestBody ProductDto.UpdateRequest requestDto,
                                                                   HttpServletRequest request) {
        ProductDto.UpdateResponse response = productService.updateProduct(id, requestDto, request);
        return ResponseEntity.ok().body(response);
    }
}
