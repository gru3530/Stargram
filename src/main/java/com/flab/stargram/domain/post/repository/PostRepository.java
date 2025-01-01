package com.flab.stargram.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.stargram.domain.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByPostId(Long postId);
}
