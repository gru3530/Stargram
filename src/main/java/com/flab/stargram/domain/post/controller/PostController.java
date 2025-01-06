package com.flab.stargram.domain.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.post.service.PostService;
import com.flab.stargram.domain.post.model.PostRequestDto;
import com.flab.stargram.domain.common.exception.InvalidInputException;
import com.flab.stargram.domain.common.model.ApiResponseEnum;
import com.flab.stargram.domain.common.model.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    @Transactional
    @PostMapping
    public ResponseEntity<ApiResult> createPost(@RequestBody PostRequestDto dto) {
        if (dto.isUserIdEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.EMPTY_USERID);
        }

        if (dto.isContentEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.EMPTY_CONTENT);
        }

        return ApiResult.success(postService.postFeed(dto));
    }

}
