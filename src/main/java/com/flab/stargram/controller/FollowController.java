package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.common.ApiResult;
import com.flab.stargram.entity.model.FollowRequestDto;
import com.flab.stargram.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/{inputUserId}")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follows")
    public ResponseEntity<ApiResult> followUser(@RequestBody FollowRequestDto dto, @PathVariable String inputUserId) {
        dto.validateEmpty().ifInvalidThrow();

        followService.followUser(FollowRequestDto.parseToLong(inputUserId), dto.getFollowingId());
        return ApiResult.success(null);
    }
}
