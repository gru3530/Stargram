package com.flab.stargram.domain.comment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flab.stargram.domain.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //SELECT * FROM comment WHERE comment_id = ?;
    Optional<Comment> findById(Long commentId);
}