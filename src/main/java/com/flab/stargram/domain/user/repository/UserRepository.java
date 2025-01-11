package com.flab.stargram.domain.user.repository;

import com.flab.stargram.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //SELECT * FROM user WHERE user_name = ?;
    Optional<User> findByUserName(String email);
    //SELECT * FROM user WHERE id = ?;
    Optional<User> findById(Long userId);
    //SELECT EXISTS (SELECT 1 FROM user WHERE email = ?);
    boolean existsByEmail(String email);
    //SELECT EXISTS (SELECT 1 FROM user WHERE userName= ?);
    boolean existsByUserName(String userName);
    //SELECT EXISTS (SELECT 1 FROM user WHERE userId= ?);
    boolean existsById(Long userId);
}
