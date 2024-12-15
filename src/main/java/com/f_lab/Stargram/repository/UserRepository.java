package com.f_lab.Stargram.repository;

import com.f_lab.Stargram.domain.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserName(String userName);
    Optional<UserInfo> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
