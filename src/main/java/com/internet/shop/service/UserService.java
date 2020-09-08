package com.internet.shop.service;

import com.internet.shop.model.User;
import java.util.List;

public interface UserService {
    User create(User user);

    User get(long id);

    List<User> getAll();

    User update(User user);

    boolean remove(Long id);
}
