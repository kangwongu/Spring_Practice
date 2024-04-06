package com.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicApplication.class, args);
    }

}

/**
 * 회원 -> 주문, 할인정책
 * 회원은 VIP, 일반 등급이 있고, 등급에 따라 할인정책이 결정된다
 * 회원 데이터는 자체 DB를 구출할 수도 있고, 외부 시스템과 연동할 수도 있다 (인터페이스로 역할-구현 분리 필요)
 */