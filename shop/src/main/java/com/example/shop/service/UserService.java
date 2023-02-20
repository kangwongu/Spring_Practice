package com.example.shop.service;

import com.example.shop.domain.User;
import com.example.shop.domain.UserRoleEnum;
import com.example.shop.dto.UserDto;
import com.example.shop.exception.UserException;
import com.example.shop.exception.status.UserStatus;
import com.example.shop.repository.UserRepository;
import com.example.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.admin.token}")
    private String ADMIN_TOKEN;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 회원가입
    public void signUp(UserDto.SignUpRequest requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserException(UserStatus.DUPLICATED_USER);
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new UserException(UserStatus.INVALID_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
        userRepository.save(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(UserDto.LoginRequest requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UserException(UserStatus.NOT_EXIST_USER)
        );

        if (!findUser.getPassword().equals(password)) {
            throw new UserException(UserStatus.NOT_CORRECT_PASSWORD);
        }

        // 응답 헤더에 JWT 토큰을 보내준다
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(findUser.getUsername(), findUser.getRole()));
    }
}
