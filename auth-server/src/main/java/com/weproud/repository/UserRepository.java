package com.weproud.repository;

import com.weproud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Logan. 81k
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String username);
}
