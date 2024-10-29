package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.dto.OneProductForm;
import com.capstone.capstoneProject.service.JsoupService;
import com.capstone.capstoneProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final JsoupService jsoupService;
    private final ProductService productService;

    @GetMapping("/prod")
    public void product() throws IOException {
        jsoupService.getProducts("https://prod.danawa.com/list/?cate=112758");
    }

    //연결이 제대로 되는지 테스트용
    @PostMapping("/one/prod")
    public List<CommentForm> getComment(@RequestBody OneProductForm oneProductForm) {
        return productService.showOneProductComment(oneProductForm.getProductName());
    }
}
