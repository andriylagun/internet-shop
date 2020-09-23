package com.internet.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private boolean available;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, boolean avaliable) {
        this.name = name;
        this.price = price;
        this.available = avaliable;
    }
}
