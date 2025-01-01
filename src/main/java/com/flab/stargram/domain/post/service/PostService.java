package com.flab.stargram.domain.post.service;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.post.model.Post;
import com.flab.stargram.domain.post.model.PostRequestDto;
import com.flab.stargram.domain.post.repository.PostRepository;
import com.flab.stargram.domain.user.exception.UserNotFoundException;
import com.flab.stargram.domain.user.model.ApiResponseEnum;
import com.flab.stargram.domain.user.service.CommonService;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommonService commonService;

    public PostService(PostRepository postRepository, CommonService commonService) {
        this.postRepository = postRepository;
        this.commonService = commonService;
    }

    public Post postFeed(PostRequestDto dto) {
        if (!findByUserId(dto)) {
            throw new UserNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        Post post = new Post(dto);
        postRepository.save(post);
        return post;
    }

    private boolean findByUserId(PostRequestDto dto) {
        return commonService.findByUserId(dto.getUserId());
    }
}
