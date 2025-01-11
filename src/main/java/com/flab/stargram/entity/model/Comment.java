package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseEntity;

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
public class Comment extends BaseEntity {
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

    private Comment(CommentRequestDto dto, Long postId) {
        this.postId = postId;
        this.comment = dto.getComment();
        this.parentCommentId = dto.getParentCommentId();
        this.userId = dto.getUserId();
    }

    public static Comment writeCommentOf(CommentRequestDto dto, Long postId) {
        return new Comment(dto, postId);
    }
}
