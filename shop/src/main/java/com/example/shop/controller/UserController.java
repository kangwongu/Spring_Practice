package com.example.shop.controller;

import com.example.shop.dto.UserDto;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    // 로그인 페이지
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDto.SignUpRequest requestDto) {
        userService.signUp(requestDto);
        return "redirect:/api/user/login";
    }

    // 로그인
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody UserDto.LoginRequest requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
        return "success";
    }
}
