package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void addComment(@RequestBody CommentForm commentForm) {
        String youtuberName = commentForm.getYoutuberName();
        int subscriber = commentForm.getSubscriber();
        String productName = commentForm.getProductName();
        String productComment = commentForm.getProductComment();

        commentService.saveComment(youtuberName, productName, subscriber, productComment);
    }

    @GetMapping("/all/comment")
    public List<Comment> showAllComment() {
        return commentService.getAllComment();
    }
}
