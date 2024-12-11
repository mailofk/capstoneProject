package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.domain.Comment;
import com.capstone.capstoneProject.dto.CommentForm;
import com.capstone.capstoneProject.dto.CommentReturnForm;
import com.capstone.capstoneProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void addComment(@RequestPart MultipartFile file, CommentForm commentForm) throws IOException {
        String youtuberName = commentForm.getYoutuberName();
        int subscriber = commentForm.getSubscriber();
        String productName = commentForm.getProductName();
        String productComment = commentForm.getProductComment();

        commentService.saveComment(youtuberName, productName, subscriber, productComment, file);
    }

    @GetMapping("/all/comment")
    public List<Comment> showAllComment() {
        return commentService.getAllComment();
    }

    @GetMapping("/comment/{id}")
    public CommentReturnForm oneComment(@PathVariable Long id) throws UnsupportedEncodingException {
        Comment comment = commentService.getOneComment(id);
        CommentReturnForm form = new CommentReturnForm();
        form.setYoutuberName(comment.getYoutuberName());
        form.setSubscriber(comment.getSubscriber());
        form.setProductName(comment.getProductName());
        form.setProductComment(comment.getProductComment());

        byte[] imgByte = comment.getImage();
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] byteEnc64 = encoder.encode(imgByte);

        String imgStr = new String(byteEnc64, "UTF-8");
        form.setImgStr(imgStr);

        return form;
    }
}
