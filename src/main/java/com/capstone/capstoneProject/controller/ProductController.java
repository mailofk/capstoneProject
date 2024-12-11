package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.dto.CommentReturnForm;
import com.capstone.capstoneProject.dto.OneProductForm;
import com.capstone.capstoneProject.service.JsoupService;
import com.capstone.capstoneProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final JsoupService jsoupService;
    private final ProductService productService;

    @GetMapping("/prod")
    public void product() throws IOException {
//        jsoupService.getProducts("https://prod.danawa.com/list/?cate=112758");
//        jsoupService.getProducts("https://prod.danawa.com/list/?cate=11353669");
         jsoupService.getProducts("https://prod.danawa.com/list/?cate=11452813");
    }



    //연결이 제대로 되는지 테스트용
    @GetMapping("/one/prod")
    public List<CommentForm> getComment(@RequestBody OneProductForm oneProductForm) {
        return productService.showOneProductComment(oneProductForm.getProductName());
    }

    @GetMapping("/prod/test")
    public Product prodTest(@RequestBody OneProductForm oneProductForm) {
        return productService.getOneProduct(oneProductForm.getProductName());
    }

    @GetMapping("/prod/test/{productName}")
    public Product prodTest(@PathVariable String productName) {
        return productService.getOneProduct(productName);
    }

    @GetMapping("/prod/comments") //product로 받는 것이 아닌 이름으로 받는 형태로?
    public List<CommentReturnForm> getCommentsByProduct(Product product) {
        return productService.getComments(product);
    }

//    @PostMapping("/prod/image")
//    public void setImage(@RequestPart MultipartFile file, String productName) throws IOException {
//        productService.saveImage(productName, file);
//    }
}
