package com.example.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class ShopController {

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }
}
