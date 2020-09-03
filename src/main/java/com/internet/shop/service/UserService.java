package com.internet.shop.service;

import com.internet.shop.model.User;
import java.util.List;

public interface UserService {
    User create(User user);

    User getUserById(long id);

    List<User> getAllUsers();
}
