package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.service.PostService;
import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.entity.common.ApiResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResult> createPost(@RequestBody PostRequestDto dto) {
        dto.validateEmpty().ifInvalidThrow();

        return ApiResult.success(postService.postFeed(dto));
    }
}
