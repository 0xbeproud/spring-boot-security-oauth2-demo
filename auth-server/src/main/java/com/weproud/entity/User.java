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
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String account;
    private String password;
    private String name;

    public User(final String account, final String password, final String name) {
        this.account = account;
        this.password = password;
        this.name = name;
    }
}
