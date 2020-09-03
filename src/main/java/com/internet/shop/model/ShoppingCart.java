package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCart {
    private Long id;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(Long userId) {
        products = new ArrayList<>();
        this.userId = userId;
    }
}
