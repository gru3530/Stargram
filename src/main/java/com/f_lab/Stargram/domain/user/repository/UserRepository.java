package com.f_lab.Stargram.domain.user.repository;

import com.f_lab.Stargram.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Set<User> findByIdIn(Set<Long> userIds);
}
