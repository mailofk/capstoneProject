package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.service.JsoupService;
import com.capstone.capstoneProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final JsoupService jsoupService;

    @GetMapping("/prod")
    public void product() throws IOException {
        jsoupService.getProducts("https://prod.danawa.com/list/?cate=11254120&15main_11_02");
    }
}
