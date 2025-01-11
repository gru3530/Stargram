package com.flab.stargram.domain.comment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.comment.model.Comment;
import com.flab.stargram.domain.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentQueryService {
    private final CommentRepository commentRepository;

    public Optional<Comment> findByCommentId(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
