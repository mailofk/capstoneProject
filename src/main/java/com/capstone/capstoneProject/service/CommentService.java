package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.repository.CommentRepository;
import com.capstone.capstoneProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public void saveComment(String youtuberName, String productName, int subscriber, String productComment) {
        Comment comment = new Comment();
        comment.setProductName(productName);
        comment.setYoutuberName(youtuberName);
        comment.setSubscriber(subscriber);
        comment.setProductComment(productComment);

        Product product = productRepository.findByProductName(productName);
        comment.setProduct(product);
        product.addComment(comment);

        productRepository.flush();
        commentRepository.save(comment);
    }

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

}
