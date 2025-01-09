package com.flab.stargram.entity.model;

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
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String content;

    public Post(PostRequestDto dto) {
        this.userId = dto.getUserId();
        this.content = dto.getContent();
    }

}
