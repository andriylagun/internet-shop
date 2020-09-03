package com.internet.shop.model;

import java.util.List;
import lombok.Data;

@Data
public class Order {
    private static long idCount = 1;
    private Long id;
    private List<Product> products;
    private Long userId;

    public Order(Long userId) {
        this.id = idCount++;
        this.userId = userId;
    }
}
