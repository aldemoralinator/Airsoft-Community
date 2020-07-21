package com.aldem.simpleapp.repository;

import com.aldem.simpleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> 
{
    User findByUsername(String username);
    User findByEmail(String email);
    User findByOpenId(String openId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByOpenId(String openId);
}
