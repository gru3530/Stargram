package com.flab.stargram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.stargram.entity.model.FollowGroup;

public interface FollowGroupRepository extends JpaRepository<FollowGroup, Long> {
    //SELECT * FROM follow_group WHERE user_id = ?
    FollowGroup findByUserId(Long userId);
    //SELECT EXISTS (SELECT 1 FROM follow_group WHERE user_id= ?);
    boolean existsByUserId(Long userId);
}
