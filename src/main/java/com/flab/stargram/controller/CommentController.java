package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.common.BaseDto;
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

    @PostMapping("/{postIdInput}/comment")
    public ResponseEntity<ApiResult> createComment(@RequestBody CommentRequestDto dto, @PathVariable String postIdInput) {
        dto.validateEmpty().ifInvalidThrow();

        Long postId = BaseDto.parseToLong(postIdInput);
        return ApiResult.success(commentService.addComment(dto, postId));
    }
}




