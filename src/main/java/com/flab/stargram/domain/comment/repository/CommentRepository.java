package com.flab.stargram.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flab.stargram.domain.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}