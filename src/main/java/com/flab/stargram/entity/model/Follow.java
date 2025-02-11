package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseEntity;
import com.flab.stargram.entity.dto.FollowPair;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FollowGroup followGroup;

    @Column(nullable = false)
    private Long followerId;

    @Column(nullable = false)
    private Long followingId;

    private Follow(FollowGroup followGroup, Long followerId, Long followingId) {
        this.followGroup = followGroup;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static Follow createFollowOf(FollowGroup followGroup, FollowPair followPair) {
        return new Follow(followGroup, followPair.getFollowerId(), followPair.getFollowingId());
    }
}
