package com.flab.stargram.entity.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.flab.stargram.entity.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FollowGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long followingId;

    @CreatedDate
    private LocalDateTime createdAt;

    private FollowGroup(Long followingId) {
        this.followingId = followingId;
    }

    public static FollowGroup create(long followerId) {
        return new FollowGroup(followerId);
    }
}
