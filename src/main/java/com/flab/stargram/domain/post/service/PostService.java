package com.flab.stargram.domain.post.service;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.post.model.Post;
import com.flab.stargram.domain.post.model.PostRequestDto;
import com.flab.stargram.domain.common.exception.UserNotFoundException;
import com.flab.stargram.domain.common.model.ApiResponseEnum;
import com.flab.stargram.domain.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostQueryService postQueryService;
    private final UserQueryService userQueryService;

    public Post postFeed(PostRequestDto dto) {
        if (!findByUserId(dto)) {
            throw new UserNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        Post post = new Post(dto);
        postQueryService.save(post);
        return post;
    }

    private boolean findByUserId(PostRequestDto dto) {
        return userQueryService.findByUserId(dto.getUserId());
    }
}
