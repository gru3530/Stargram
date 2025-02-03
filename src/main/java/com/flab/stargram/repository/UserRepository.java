package com.flab.stargram.repository;

import com.flab.stargram.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //SELECT * FROM user WHERE user_name = ?;
    User findByUserName(String userName);
    //SELECT * FROM user WHERE user_id = ?;
    Optional<User> findById(Long userId);
    //SELECT EXISTS (SELECT 1 FROM user WHERE user_id= ?);
    boolean existsById(Long userId);

    User findByUserNameOrEmail(String userName, String email);
}
