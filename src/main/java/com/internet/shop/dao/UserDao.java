package com.internet.shop.dao;

import com.internet.shop.model.User;
import java.util.List;

public interface UserDao {
    boolean addUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();
}
