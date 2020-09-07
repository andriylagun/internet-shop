package com.internet.shop;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import com.internet.shop.service.impl.OrderServiceImpl;
import com.internet.shop.service.impl.ShoppingCartServiceImpl;
import com.internet.shop.service.impl.UserServiceImpl;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        UserService userService = (UserService) injector.getInstance(UserServiceImpl.class);
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector.getInstance(ShoppingCartServiceImpl.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderServiceImpl.class);
        User client = new User("Andriy", "andriylagun", "12345678");
        User secondUser = new User("Jonathan", "john233", "qwersdty");
        userService.create(client);
        userService.create(secondUser);
        ShoppingCart shoppingCart = new ShoppingCart(1L);
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        userService.remove(1L);
        System.out.println("Deleting first user");
        for (User user : userService.getAll()) {
            System.out.println(user);
        }
        System.out.println("Getting second user");
        System.out.println(userService.get(2L));
        User tempUser = userService.get(3L);
        tempUser.setName("Bob");
        userService.update(tempUser);
        System.out.println("Updating 3 user");
        System.out.println(userService.get(3L));
        Product iphone = new Product("Iphone", 800.50);
        productService.create(iphone);
        Product xiaomi = new Product("Xiaomi Redmi Note 6", 400.0);
        productService.create(xiaomi);
        Product nokia = new Product("Nokia 1100", 10.50);
        productService.create(nokia);
        System.out.println("All products:");
        Storage.productStorage.forEach(System.out::println);
        System.out.println("Xiaomi Redmi Note 6 update:");
        Product productToUpdate = new Product("Xiaomi Redmi Note 6", 200.0);
        productToUpdate.setId(xiaomi.getId());
        productService.update(productToUpdate);
        System.out.println(productService.get(xiaomi.getId()));
        System.out.println("Deleting iphone:");
        productService.delete(iphone.getId());
        Storage.productStorage.forEach(System.out::println);
        ShoppingCart firstCart = new ShoppingCart(3L);
        firstCart.getProducts().add(new Product("sword", 34.5));
        shoppingCartService.create(firstCart);
        ShoppingCart secondCart = new ShoppingCart(2L);
        secondCart.getProducts().add(new Product("axe", 56D));
        shoppingCartService.create(secondCart);
        System.out.println("Getting by user id = 3");
        System.out.println(shoppingCartService.getByUserId(3L));
        shoppingCartService.addProduct(firstCart, xiaomi);
        System.out.println("Adding product");
        System.out.println(firstCart);
        shoppingCartService.deleteProduct(firstCart, xiaomi);
        System.out.println("Deleting product");
        System.out.println(firstCart);
        shoppingCartService.clear(firstCart);
        System.out.println("Cleaning firstCart");
        System.out.println(firstCart);
        Order firstOrder = new Order(3L);
        Order secondOrder = new Order(2L);
        System.out.println("Getting user`s orders");
        for (Order order : orderService.getUserOrders(3L)) {
            System.out.println(order);
        }
        System.out.println("Completing order");
        System.out.println(orderService.completeOrder(firstCart));
        orderService.delete(1L);
        System.out.println("Deleting Order");
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
    }
}
