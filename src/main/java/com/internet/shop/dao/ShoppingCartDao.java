package com.internet.shop.dao;

import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();

    Optional<ShoppingCart> getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);
}
