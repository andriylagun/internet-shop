package com.internet.shop.dao.impl;

import com.internet.shop.dao.UserDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.User;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.userStorage
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.userStorage;
    }

    @Override
    public User update(User user) {
        User updatedUser = get(user.getId()).get();
        updatedUser.setLogin(user.getLogin());
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.userStorage
                .removeIf(user -> user.getId().equals(id));
    }
}
