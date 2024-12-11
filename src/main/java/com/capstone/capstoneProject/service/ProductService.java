package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.dto.CommentReturnForm;
import com.capstone.capstoneProject.repository.CommentRepository;
import com.capstone.capstoneProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;


    public void saveProduct(String prodName, String prodLink, String smlCategory, String cpu, String ram, String capacity, String weight) throws IOException {
        if (productRepository.existsByProductName(prodName)) {
            return;
        }
        if (productRepository.existsById(60L)) {
            return;
        }

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

//    public void saveImage(String productName, MultipartFile file) throws IOException {
//        Product product = productRepository.findByProductName(productName);
//        product.setImage(file.getBytes());
//        productRepository.save(product);
//    }

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

    public Product getOneProduct(String prodName) {
        return productRepository.findByProductName(prodName);
    }

    public Product getOneProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    public List<CommentReturnForm> getComments(Product product) {
        List<Comment> allComment = commentRepository.findAllByProductName(product.getProductName());
        List<CommentReturnForm> list = new ArrayList<>();
        for (Comment comment : allComment) {
            CommentReturnForm form = new CommentReturnForm();
            form.setYoutuberName(comment.getYoutuberName());
            form.setProductComment(comment.getProductComment());
            form.setProductName(comment.getProductName());
            form.setSubscriber(comment.getSubscriber());

            list.add(form);
        }

        return list;

    }



}
