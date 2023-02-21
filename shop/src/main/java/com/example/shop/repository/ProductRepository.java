package com.example.shop.repository;

import com.example.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId);
    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
