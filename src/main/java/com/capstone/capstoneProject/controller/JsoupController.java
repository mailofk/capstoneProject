package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.domain.Category;
import com.capstone.capstoneProject.service.CategoryService;
import com.capstone.capstoneProject.service.JsoupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class JsoupController {

    private final JsoupService jsoupService;
    private final CategoryService categoryService;

    @GetMapping("/api/selenium")
    public void tryinging() throws IOException {
        jsoupService.useSelenium();

    }

    @GetMapping("/test")
    public void testtest() {
        jsoupService.testing();
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.findAllCategory();
    }


}
