package com.example.post.util;

import com.example.post.security.userdetails.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private final String SECRET_KEY;
    public final int ACCESS_EXPIRATION_TIME;
    public final BigInteger REFRESH_EXPIRATION_TIME;
    private Key key;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtTokenProvider(@Value("${spring.jwt.secret-key}") String SECRET_KEY,
                            @Value("${spring.jwt.access-expiration-time}") int ACCESS_EXPIRATION_TIME,
                            @Value("${spring.jwt.refresh-expiration-time}") BigInteger REFRESH_EXPIRATION_TIME,
                            UserDetailsServiceImpl userDetailsService) {
        this.SECRET_KEY = SECRET_KEY;
        this.ACCESS_EXPIRATION_TIME = ACCESS_EXPIRATION_TIME * 60;
        this.REFRESH_EXPIRATION_TIME = REFRESH_EXPIRATION_TIME;
        this.userDetailsService = userDetailsService;
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
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            log.info("token = {}", token.substring(7));
            return token.substring(7);
        }
        return null;
    }

    // 토큰 검증
    public Boolean validateToken(String token) {
        return !Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token).getBody().isEmpty();
    }

    // AccessToken에서 이메일 정보 추출
    public String getUserEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token).getBody().getSubject();
    }

    // 인증 객체 생성
    public Authentication createAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }
}
