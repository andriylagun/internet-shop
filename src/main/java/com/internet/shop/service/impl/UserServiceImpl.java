package com.internet.shop.service.impl;

import com.internet.shop.dao.UserDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean remove(Long id) {
        return userDao.remove(id);
    }
}