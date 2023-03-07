package com.example.shop.service;

import com.example.shop.domain.Folder;
import com.example.shop.domain.Product;
import com.example.shop.domain.User;
import com.example.shop.domain.UserRoleEnum;
import com.example.shop.dto.ProductDto;
import com.example.shop.exception.FolderException;
import com.example.shop.exception.ProductException;
import com.example.shop.exception.UserException;
import com.example.shop.exception.status.FolderStatus;
import com.example.shop.exception.status.ProductStatus;
import com.example.shop.exception.status.UserStatus;
import com.example.shop.repository.FolderRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;
    private final JwtUtil jwtUtil;

    // 관심 상품 등록
    public ProductDto.RegisterResponse registerProduct(ProductDto.RegisterRequest requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new UserException(UserStatus.INVALID_ACCESS_TOKEN);
            }

            User findUser = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UserException(UserStatus.NOT_EXIST_USER)
            );

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
        } else {
            return null;
        }
    }

    // 관심 상품 조회
    @Transactional(readOnly = true)
    public Page<ProductDto.ReadResponse> getProducts(HttpServletRequest request, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;    // 정렬 방향
        Sort sort = Sort.by(direction, sortBy); // 무엇을 기준으로 정렬할건지
        Pageable pageable = PageRequest.of(page, size, sort);

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

            Page<ProductDto.ReadResponse> response;
            Page<Product> products;

            if (role == UserRoleEnum.USER) {
                products = productRepository.findAllByUserId(findUser.getId(), pageable);
            } else {
                products = productRepository.findAll(pageable);
            }

            response = products.map(product -> new ProductDto.ReadResponse(product));
            return response;
        } else {
            return null;
        }
    }

    public ProductDto.UpdateResponse updateProduct(Long id, ProductDto.UpdateRequest requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new UserException(UserStatus.INVALID_ACCESS_TOKEN);
            }

            User findUser = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UserException(UserStatus.INVALID_ACCESS_TOKEN)
            );

            Product findProduct = productRepository.findById(id).orElseThrow(
                    () -> new ProductException(ProductStatus.NOT_EXIST_PRODUCT)
            );

            findProduct.updateMyPrice(requestDto.getMyPrice());
            return ProductDto.UpdateResponse.builder()
                    .id(findProduct.getId()).build();
        } else {
            return null;
        }
    }

    public ProductDto.AddFolderResponse addFolder(Long productId, Long folderId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new UserException(UserStatus.INVALID_ACCESS_TOKEN);
            }

            User findUser = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UserException(UserStatus.INVALID_ACCESS_TOKEN)
            );

            Product findProduct = productRepository.findById(productId).orElseThrow(
                    () -> new ProductException(ProductStatus.NOT_EXIST_PRODUCT)
            );

            Folder findFolder = folderRepository.findById(folderId).orElseThrow(
                    () -> new FolderException(FolderStatus.NOT_EXIST_FOLDER)
            );

            if (!findProduct.getUserId().equals(findUser.getId())) {
                throw new UserException(UserStatus.INCORRECT_MY_PRODUCT);
            }

            if (!findFolder.getUser().getId().equals(findUser.getId())) {
                throw new UserException(UserStatus.INCORRECT_MY_FOLDER);
            }

            findProduct.addFolder(findFolder);

            return new ProductDto.AddFolderResponse(findProduct.getId());
        } else {
            return null;
        }
    }
}
