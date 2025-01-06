package com.flab.stargram.domain.comment.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String comment;

    private Long parentCommentId;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public Comment(CommentRequestDto dto, Long postId) {
        this.postId = postId;
        this.comment = dto.getComment();
        this.parentCommentId = dto.getParentCommentId();
        this.userId = dto.getUserId();
    }
}
