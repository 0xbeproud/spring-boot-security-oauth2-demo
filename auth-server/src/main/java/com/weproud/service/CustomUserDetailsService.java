package com.weproud.service;

import com.weproud.domain.AuthUser;
import com.weproud.entity.User;
import com.weproud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author Logan. 81k
 */

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByAccount(username);
        log.info("member: {}", user);
        if (ObjectUtils.isEmpty(user))
            throw new UsernameNotFoundException(username);
        return new AuthUser(user);
    }
}
