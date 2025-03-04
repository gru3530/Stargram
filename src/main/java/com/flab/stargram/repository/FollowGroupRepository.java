package com.flab.stargram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.stargram.entity.model.FollowGroup;

public interface FollowGroupRepository extends JpaRepository<FollowGroup, Long> {
    //SELECT * FROM follow_group WHERE user_id = ?
    FollowGroup findByFollowingId(Long followingId);
    //SELECT COUNT(*) > 0 FROM follow_group WHERE following_id = ?
    boolean existsByFollowingId(Long followingId);
    //SELECT COUNT(*) > 0 FROM follow_group WHERE follower_id = ?
    boolean existsByFollowerId(Long followerId);
}
