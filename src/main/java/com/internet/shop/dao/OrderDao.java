package com.internet.shop.dao;

import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(Long userId);
}
