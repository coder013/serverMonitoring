package com.sm.console.repository;

import com.sm.console.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginId(String loginId);
}
