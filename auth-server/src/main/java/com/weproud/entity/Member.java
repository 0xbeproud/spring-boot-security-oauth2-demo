package com.weproud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Logan. 81k
 */
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Member implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    public Member(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
