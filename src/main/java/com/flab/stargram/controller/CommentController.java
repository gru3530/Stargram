package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.model.CommentRequestDto;
import com.flab.stargram.service.CommentService;
import com.flab.stargram.config.exception.InvalidInputException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ApiResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @Transactional
    @PostMapping("/{postIdInput}/comment")
    public ResponseEntity<ApiResult> createComment(@RequestBody CommentRequestDto dto, @PathVariable String postIdInput) {
        Long postId = parsePostId(postIdInput);

        if (dto.isUserIdEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.EMPTY_USERID);
        }

        if (dto.isCommentEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.EMPTY_CONTENT);
        }

        return ApiResult.success(commentService.addComment(dto, postId));
    }

    private Long parsePostId(String postId) {
        if (postId.isEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.EMPTY_POSTID);
        }

        try {
            return Long.parseLong(postId);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }

    }

}




