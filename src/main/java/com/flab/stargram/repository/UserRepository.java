package com.flab.stargram.repository;

import com.flab.stargram.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //SELECT * FROM user WHERE user_name = ?
    User findByUserName(String userName);
    //SELECT * FROM user where id = ?
    User findById(long id);
    //SELECT COUNT(*) > 0 FROM user WHERE email = ?
    boolean existsByEmail(String email);
    //SELECT COUNT(*) > 0 FROM user WHERE user_name = ?
    boolean existsByUserName(String userName);
    //SELECT COUNT(*) > 0 FROM user WHERE user_id = ?
    boolean existsById(Long userId);
    //SELECT * FROM user WHERE user_name = ? OR email = ?
    User findByUserNameOrEmail(String userName, String email);
}
