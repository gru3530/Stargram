package com.flab.stargram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.stargram.entity.model.FollowGroup;

public interface FollowGroupRepository extends JpaRepository<FollowGroup, Long> {
    FollowGroup findByUserId(Long userId);

}
