package com.example.shop.controller;

import com.example.shop.dto.FolderDto;
import com.example.shop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final FolderService folderService;

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }

    // 폴더 조회
    @GetMapping("/user-folder")
    public String getFolders(HttpServletRequest request, Model model) {
        List<FolderDto.ReadResponse> response = folderService.getFolders(request);

        model.addAttribute("folders", response);
        return "/index :: #fragment";
    }
}
