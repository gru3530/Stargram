package com.flab.stargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.domain.fanout.service.FanoutService;
import com.flab.stargram.entity.model.Post;
import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FanoutService fanoutService;

    @Transactional
    public Post postFeed(PostRequestDto dto) {
        if (!findByUserId(dto)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        Post post = Post.writePostOf(dto);
        postRepository.save(post);

        fanoutService.distributeToFollowers(post);
        return post;
    }

    public boolean hasPostId(Long postId) {
        return postRepository.existsByPostId(postId);
    }

    private boolean findByUserId(PostRequestDto dto) {
        return userService.hasUserId(dto.getUserId());
    }
}
