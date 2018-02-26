package com.weproud.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Logan. 81k
 */
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;

    public Member(final String name, final String username, final String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
