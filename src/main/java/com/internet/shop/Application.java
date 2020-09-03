package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");
    static int x =10;
    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product iphone = new Product("Iphone", 800.50);
        productService.create(iphone);
        Product xiaomi = new Product("Xiaomi Redmi Note 6", 400.0);
        productService.create(xiaomi);
        Product nokia = new Product("Nokia 1100", 10.50);
        productService.create(nokia);
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Xiaomi Redmi Note 6 update:");
        Product productToUpdate = new Product("Xiaomi Redmi Note 6", 200.0);
        productToUpdate.setId(xiaomi.getId());
        productService.update(productToUpdate);
        System.out.println(productService.get(xiaomi.getId()));
        System.out.println("Deleting iphone:");
        productService.delete(iphone.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
