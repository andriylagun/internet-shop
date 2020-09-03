package com.internet.shop.model;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCart {
    private static long idCount = 1;
    private Long id;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(Long userId) {
        this.id = idCount++;
        this.userId = userId;
    }
}
