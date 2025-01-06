package com.flab.stargram.domain.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.comment.model.CommentRequestDto;
import com.flab.stargram.domain.comment.service.CommentService;
import com.flab.stargram.domain.common.exception.InvalidInputException;
import com.flab.stargram.domain.common.model.ApiResponseEnum;
import com.flab.stargram.domain.common.model.ApiResult;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @Transactional
    @PostMapping("/{userid}/comments")
    public ResponseEntity<ApiResult> createComment(@RequestBody CommentRequestDto dto, @PathVariable Long userid) {
        if(dto.isPostIdEmpty()){
            throw new InvalidInputException(ApiResponseEnum.EMPTY_POSTID);
        }

        if(dto.isCommentEmpty()){
            throw new InvalidInputException(ApiResponseEnum.EMPTY_CONTENT);
        }

        return ApiResult.success(commentService.addComment(dto,userid));
    }


}




