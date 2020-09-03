package com.internet.shop.model;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCart {
    private static long idCount = 1;
    private Long id;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(List<Product> products, Long userId) {
        this.id = idCount++;
        this.products = products;
        this.userId = userId;
    }
}
