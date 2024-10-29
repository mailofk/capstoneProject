package com.capstone.capstoneProject.repository;

import com.capstone.capstoneProject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
