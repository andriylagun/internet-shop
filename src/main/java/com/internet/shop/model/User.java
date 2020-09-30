package com.internet.shop.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private byte[] salt;
    private Set<Role> roles;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = new HashSet<>();
    }
}
