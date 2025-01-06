package com.flab.stargram.domain.post.service;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.post.model.Post;
import com.flab.stargram.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostQueryService {
    private final PostRepository postRepository;

    public boolean existsByPostId(final Long postId) {
        return postRepository.existsById(postId);
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
