package com.internet.shop.dao.impl;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
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
        IntStream.range(0, Storage.productStorage.size())
                .filter(index -> Storage.productStorage.get(index)
                        .getId()
                        .equals(product.getId()))
                .forEach(index -> Storage.productStorage.set(index, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.productStorage.removeIf(product -> product.getId().equals(id));
    }
}
