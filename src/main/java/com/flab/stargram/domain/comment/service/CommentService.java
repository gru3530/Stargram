package com.flab.stargram.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.domain.comment.model.Comment;
import com.flab.stargram.domain.comment.model.CommentRequestDto;
import com.flab.stargram.domain.comment.repository.CommentRepository;
import com.flab.stargram.domain.common.exception.DataNotFoundException;
import com.flab.stargram.domain.common.exception.InvalidInputException;
import com.flab.stargram.domain.common.model.ApiResponseEnum;
import com.flab.stargram.domain.post.service.PostQueryService;
import com.flab.stargram.domain.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentQueryService commentQueryService;
    private final UserQueryService userQueryService;
    private final PostQueryService postQueryService;

    @Transactional
    public Comment addComment(CommentRequestDto dto, Long userId) {
        if(!isUserIdValid(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        if(!isPostIdValid(dto)){
            throw new DataNotFoundException(ApiResponseEnum.POST_NOT_FOUND);
        }

        if(!isCommentValid(dto)){
            throw new InvalidInputException(ApiResponseEnum.NESTED_COMMENT);
        }

        Comment comment = new Comment(dto, userId);
        commentQueryService.save(comment);
        return comment;
    }

    private boolean isUserIdValid(Long userId) {
        return userQueryService.existByUserId(userId);
    }

    private boolean isPostIdValid(CommentRequestDto dto) {
        return postQueryService.existsByPostId(dto.getPostId());
    }

    private boolean isCommentValid(CommentRequestDto dto) {
        Comment parentComment = commentQueryService.findByCommentId(dto.getParentCommentId())
            .orElseThrow(() -> new DataNotFoundException(ApiResponseEnum.COMMENT_NOT_FOUND));

        return parentComment.getParentCommentId() != null;
    }


}
