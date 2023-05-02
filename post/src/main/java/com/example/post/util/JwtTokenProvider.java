package com.example.post.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    private final String SECRET_KEY;
    public final int ACCESS_EXPIRATION_TIME;
    public final BigInteger REFRESH_EXPIRATION_TIME;
    private Key key;


    public JwtTokenProvider(@Value("${spring.jwt.secret-key}") String SECRET_KEY,
                            @Value("${spring.jwt.access-expiration-time}") int ACCESS_EXPIRATION_TIME,
                            @Value("${spring.jwt.refresh-expiration-time}") BigInteger REFRESH_EXPIRATION_TIME) {
        this.SECRET_KEY = SECRET_KEY;
        this.ACCESS_EXPIRATION_TIME = ACCESS_EXPIRATION_TIME * 60;
        this.REFRESH_EXPIRATION_TIME = REFRESH_EXPIRATION_TIME;
    }

    // 토큰 서명 시, key가 필요
    @PostConstruct
    public void init() {
        byte[] decodeSecretKey = Base64.getDecoder().decode(SECRET_KEY);
        key = Keys.hmacShaKeyFor(decodeSecretKey);
    }

    // AccessToken 생성
    public String generateAccessToken(String email) {
        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // AccessToken 정보 추출

    // 토큰 검증

}
