package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void saveProduct(String prodName, String prodLink, String smlCategory, String cpu, String ram, String capacity, String weight) throws IOException {
        Product product = new Product();
        product.setProductName(prodName);
        product.setLink(prodLink);
        product.setCpu(cpu);
        product.setRam(ram);
        product.setCapacity(capacity);
        product.setWeight(weight);

        product.setSmlCategory(smlCategory);

        productRepository.save(product);
    }

    public List<CommentForm> showOneProductComment(String productName) {

        Product product = productRepository.findByProductName(productName);
        List<Comment> allComment = product.getCommentList();
        List<CommentForm> allCommentForm = new ArrayList<>();

        for (Comment comment : allComment) {
            CommentForm commentForm = new CommentForm();
            commentForm.setProductComment(comment.getProductComment());
            commentForm.setProductName(comment.getProductName());
            commentForm.setSubscriber(comment.getSubscriber());
            commentForm.setYoutuberName(comment.getYoutuberName());

            allCommentForm.add(commentForm);
        }

        return allCommentForm;
    }



}
