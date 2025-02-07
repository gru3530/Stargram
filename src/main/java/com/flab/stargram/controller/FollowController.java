package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.common.ApiResult;
import com.flab.stargram.entity.common.ParseUtil;
import com.flab.stargram.entity.dto.FollowPair;
import com.flab.stargram.entity.dto.FollowRequestDto;
import com.flab.stargram.service.FollowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/{inputUserId}")
@RequiredArgsConstructor
@Validated
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<ApiResult> followUser(@Valid @RequestBody FollowRequestDto dto, @PathVariable String inputUserId) {
        FollowPair followPair = FollowPair.createFollowPairOf(inputUserId, dto);

        followService.followUser(followPair);
        return ApiResult.success(null);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<ApiResult> unfollowUser(@Valid @RequestBody FollowRequestDto dto, @PathVariable String inputUserId) {
        FollowPair followPair = FollowPair.createFollowPairOf(inputUserId, dto);

        followService.unfollowUser(followPair);
        return ApiResult.success(null);
    }

    @GetMapping("/followers")
    public ResponseEntity<ApiResult> followUsers(@PathVariable String inputUserId) {
        Long userId = ParseUtil.parseToLong(inputUserId);

        return ApiResult.success(followService.getFollowers(userId));
    }
}
