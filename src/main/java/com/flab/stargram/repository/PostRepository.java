package com.flab.stargram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.stargram.entity.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //SELECT EXISTS (SELECT 1 FROM post WHERE post_id = ?);
    boolean existsByPostId(Long postId);
}
