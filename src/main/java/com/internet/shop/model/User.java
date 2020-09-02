package com.internet.shop.model;

import lombok.Data;

@Data
public class User {
    private static long idCount = 1;
    private long id;
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) {
        this.id = idCount++;
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
