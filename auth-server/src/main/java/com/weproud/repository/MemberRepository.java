package com.weproud.repository;

import com.weproud.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Logan. 81k
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
