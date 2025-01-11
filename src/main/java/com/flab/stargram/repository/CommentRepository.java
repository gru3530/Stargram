package com.flab.stargram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flab.stargram.entity.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //SELECT * FROM comment WHERE comment_id = ?;
    Optional<Comment> findByCommentId(Long commentId);
}