package com.internet.shop.security;

import com.internet.shop.exception.AuthException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthException {
        Optional<User> user = userService.findByLogin(login);
        if (user.isEmpty() || !password.equals(user.get().getPassword())) {
            throw new AuthException("Your login or password is incorrect. Please try again.");
        }
        return user.get();
    }
}
