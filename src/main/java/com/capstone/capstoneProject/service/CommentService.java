package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.domain.Product;
import com.capstone.capstoneProject.repository.CommentRepository;
import com.capstone.capstoneProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public void saveComment(String youtuberName, String productName, int subscriber, String productComment, MultipartFile file) throws IOException {
        Comment comment = new Comment();
        comment.setProductName(productName);
        comment.setYoutuberName(youtuberName);
        comment.setSubscriber(subscriber);
        comment.setProductComment(productComment);
        comment.setImage(file.getBytes());

        Product product = productRepository.findByProductName(productName);
        comment.setProduct(product);
        product.addComment(comment);

        productRepository.flush();
        commentRepository.save(comment);
    }

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public Comment getOneComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow();
    }

}
