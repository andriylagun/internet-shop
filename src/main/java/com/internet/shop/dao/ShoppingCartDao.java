package com.internet.shop.dao;

import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();

    ShoppingCart getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);
}
