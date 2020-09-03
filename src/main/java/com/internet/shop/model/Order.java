package com.internet.shop.model;

import java.util.List;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;

    public Order(Long userId) {
        this.products = products;
        this.userId = userId;
    }
}
