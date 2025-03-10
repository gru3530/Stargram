package com.flab.stargram.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.stargram.entity.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    //SELECT COUNT(*) > 0 FROM follow WHERE follower_id = ? AND following_id = ?
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    //DELETE FROM follow WHERE follower_id = ? AND following_id = ?
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);
    //SELECT COUNT(*) FROM follow WHERE following_id = ?;
    long countByFollowerId(Long followerId);
    //SELECT * FROM follow WHERE following_id = ?;
    List<Follow> findByFollowingId(Long followingId);
    //SELECT * FROM follow WHERE follower_id = ? ORDER BY id ASC LIMIT ? OffSET ?;
    List<Follow> findByFollowerId(Long followerId);
}
