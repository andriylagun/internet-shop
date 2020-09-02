package com.internet.shop.model;

import lombok.Data;

@Data
public class Product {
    private static long idCount = 1;
    private Long id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.id = idCount++;
        this.name = name;
        this.price = price;
    }
}
