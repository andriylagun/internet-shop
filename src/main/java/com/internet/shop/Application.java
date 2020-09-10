package com.internet.shop;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        User client = new User("Andriy", "andriylagun", "12345678");
        userService.create(client);
        ShoppingCart firstCart = new ShoppingCart(1L);
        shoppingCartService.create(firstCart);
        User secondClient = new User("Jonathan", "john233", "qwersdty");
        userService.create(secondClient);
        ShoppingCart secondCart = new ShoppingCart(2L);
        shoppingCartService.create(secondCart);
        User userForDelete = new User("Michael", "michael123", "qwersdty");
        userService.create(userForDelete);
        System.out.println("Get all users: ");
        userService.getAll().forEach(System.out::println);
        userService.delete(3L);
        System.out.println("Deleting third user");
        userService.getAll().forEach(System.out::println);
        System.out.println("Getting second user");
        System.out.println(userService.get(2L));
        User tempUser = new User("Andriy", "andriylagun", "12345");
        tempUser.setId(client.getId());
        userService.update(tempUser);
        System.out.println(userService.get(1L));
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product iphone = new Product("Iphone", 800.50);
        productService.create(iphone);
        Product xiaomi = new Product("Xiaomi Redmi Note 6", 400.0);
        productService.create(xiaomi);
        Product nokia = new Product("Nokia 1100", 10.50);
        productService.create(nokia);
        Product samsung = new Product("Samsung A100", 100000.0);
        productService.create(samsung);
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
        System.out.println("Getting shoppingCart by user id = 2");
        System.out.println(shoppingCartService.getByUserId(2L));
        shoppingCartService.addProduct(firstCart, samsung);
        shoppingCartService.addProduct(firstCart, xiaomi);
        shoppingCartService.addProduct(secondCart, nokia);
        shoppingCartService.addProduct(secondCart, samsung);
        System.out.println("Adding product");
        System.out.println(firstCart);
        shoppingCartService.deleteProduct(firstCart, samsung);
        System.out.println("Deleting product");
        System.out.println(firstCart);
        shoppingCartService.clear(firstCart);
        System.out.println("Cleaning firstCart");
        System.out.println(firstCart);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        System.out.println("Completing orders");
        System.out.println(orderService.completeOrder(firstCart));
        System.out.println(orderService.completeOrder(secondCart));
        System.out.println("Getting user`s orders");
        System.out.println(orderService.getUserOrders(2L));
        orderService.delete(1L);
        System.out.println("Deleting Order");
        orderService.getAll().forEach(System.out::println);
    }
}
