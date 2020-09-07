package com.internet.shop.dao.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCartStorage.size())
                .filter(index -> Storage.shoppingCartStorage.get(index).getId()
                        .equals(shoppingCart.getId()))
                .forEach(index -> Storage.shoppingCartStorage.set(index, shoppingCart));
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCartStorage;
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return Storage.shoppingCartStorage.stream()
                .filter(shoppingCart -> shoppingCart
                        .getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return Storage.shoppingCartStorage.remove(shoppingCart);
    }
}
