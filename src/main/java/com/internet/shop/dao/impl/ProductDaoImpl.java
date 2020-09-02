package com.internet.shop.dao.impl;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.productStorage.add(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.productStorage.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.productStorage;
    }

    @Override
    public Product update(Product product) {
        return Storage.productStorage.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .map(p -> p = product)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.productStorage
                .remove(get(id).orElseThrow());
    }
}
