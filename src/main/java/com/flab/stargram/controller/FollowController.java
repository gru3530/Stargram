package com.flab.stargram.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.common.ApiResult;
import com.flab.stargram.entity.common.ParseUtil;
import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.service.FollowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public ApiResult followUser(@Valid @RequestBody FollowDto dto) {
        followService.followUser(dto);
        return ApiResult.success();
    }

    @PostMapping("/unfollow")
    public ApiResult unfollowUser(@Valid @RequestBody FollowDto dto) {
        followService.unfollowUser(dto);
        return ApiResult.success();
    }

    @GetMapping("/{inputUserId}/followers")
    public ApiResult followUsers(@PathVariable String inputUserId) {
        return ApiResult.success(followService.getFollowerIds(ParseUtil.parseToLong(inputUserId)));
    }
}
