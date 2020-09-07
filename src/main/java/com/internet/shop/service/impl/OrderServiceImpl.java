package com.internet.shop.service.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        List<Product> products = List.copyOf(shoppingCart.getProducts());
        Long userId = shoppingCart.getUserId();
        Order order = new Order(userId);
        order.setProducts(products);
        orderDao.create(order);
        shoppingCart.getProducts().clear();
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order create(Order element) {
        return null;
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id)
                .orElseThrow();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order element) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
