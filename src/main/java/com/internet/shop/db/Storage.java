package com.internet.shop.db;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<User> userStorage = new ArrayList<>();
    public static final List<Product> productStorage = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCartStorage = new ArrayList<>();
    public static final List<Order> orderStorage = new ArrayList<>();
}
