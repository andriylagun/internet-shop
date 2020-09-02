package com.internet.shop.dao.impl;

import com.internet.shop.dao.UserDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.User;
import java.util.List;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        return Storage.userStorage.add(user);
    }

    @Override
    public User getUserById(long id) {
        return Storage.userStorage
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.userStorage;
    }
}
