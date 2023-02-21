package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.domain.User;
import com.example.shop.domain.UserRoleEnum;
import com.example.shop.dto.ProductDto;
import com.example.shop.exception.ProductException;
import com.example.shop.exception.UserException;
import com.example.shop.exception.status.ProductStatus;
import com.example.shop.exception.status.UserStatus;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

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
    public List<ProductDto.ReadResponse> getProducts(HttpServletRequest request) {
        // Http 요청 헤더에서 토큰을 가져옮
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // 토큰 유효성 검사
            if (jwtUtil.validateToken(token)) {
                // 유효하면 JWT 토큰으로부터 정보를 얻어옮
                claims = jwtUtil.getUserInfoFromToken(token);
                log.info("token!!! = {}", claims);
            } else {
                throw new UserException(UserStatus.INVALID_ACCESS_TOKEN);
            }

            // JWT 토큰으로부터 얻은 정보로 DB 조회
            User findUser = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UserException(UserStatus.NOT_EXIST_USER)
            );

            UserRoleEnum role = findUser.getRole();
            log.info("user role!!! = {}", role);

            List<ProductDto.ReadResponse> response;
            List<Product> products;

            if (role == UserRoleEnum.USER) {
                products = productRepository.findAllByUserId(findUser.getId());
            } else {
                products = productRepository.findAll();
            }

            response = products.stream().map(product -> new ProductDto.ReadResponse(product)).collect(Collectors.toList());
            return response;
        } else {
            return null;
        }
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
