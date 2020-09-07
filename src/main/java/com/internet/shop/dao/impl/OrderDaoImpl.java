package com.internet.shop.dao.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.lib.Inject;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orderStorage
                .stream()
                .filter(order -> order.getUserId()
                        .equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order create(Order element) {
        return null;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orderStorage
                .stream()
                .filter(order -> order.getId()
                        .equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orderStorage;
    }

    @Override
    public Order update(Order element) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orderStorage
                .removeIf(order -> order.getId().equals(id));
    }
}
