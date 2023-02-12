package com.example.shop.controller;

import com.example.shop.dto.NaverApiDto;
import com.example.shop.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NaverApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")
    public List<NaverApiDto.ResponseDto> searchItems(@RequestParam String query) {
        return naverApiService.searchItems(query);
    }
}
