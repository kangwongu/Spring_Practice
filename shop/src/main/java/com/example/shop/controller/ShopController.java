package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ShopController {

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }
}
