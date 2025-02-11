package com.flab.stargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.entity.model.Comment;
import com.flab.stargram.entity.dto.CommentRequestDto;
import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.InvalidInputException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;
    private final PostService postService;

    @Transactional
    public Comment addComment(CommentRequestDto dto, Long postId) {
        if(!isUserIdValid(dto)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        if(!isPostIdValid(postId)){
            throw new DataNotFoundException(ApiResponseEnum.POST_NOT_FOUND);
        }

        if(!isCommentValid(dto)){
            throw new InvalidInputException(ApiResponseEnum.NESTED_COMMENT);
        }

        Comment comment = Comment.writeCommentOf(dto, postId);
        commentRepository.save(comment);
        return comment;
    }

    private boolean isUserIdValid(CommentRequestDto dto) {
        return userService.hasUserId(dto.getUserId());
    }

    private boolean isPostIdValid(Long postId) {
        return postService.hasPostId(postId);
    }

    private boolean isCommentValid(CommentRequestDto dto) {
        if(dto.getParentCommentId() == null){
            return true;
        }

        Comment parentComment = commentRepository.findByCommentId(dto.getParentCommentId())
            .orElseThrow(() -> new DataNotFoundException(ApiResponseEnum.COMMENT_NOT_FOUND));

        return parentComment.getParentCommentId() == null;
    }
}
