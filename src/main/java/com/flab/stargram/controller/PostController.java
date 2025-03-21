package com.flab.stargram.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.service.PostService;
import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.entity.common.ApiResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResult createPost(@Valid @RequestBody PostRequestDto dto) {
        return ApiResult.success(postService.postFeed(dto));
    }
}
