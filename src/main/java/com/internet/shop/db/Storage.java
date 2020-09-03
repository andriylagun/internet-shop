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
    private static long userId = 1;
    private static long productId = 1;
    private static long shoppingCartId = 1;
    private static long orderId = 1;

    public static void addUser(User user) {
        user.setId(userId++);
        userStorage.add(user);
    }

    public static void addProduct(Product product) {
        product.setId(productId++);
        productStorage.add(product);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(shoppingCartId++);
        shoppingCartStorage.add(shoppingCart);
    }

    public static void addOrder(Order order) {
        order.setId(orderId++);
        orderStorage.add(order);
    }
}
