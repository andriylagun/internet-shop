package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        String insertProductQuery
                = "INSERT INTO products (name, price) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(insertProductQuery,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    product.setId(resultSet.getLong(1));
                }
                return product;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create product" + product, e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String selectProductQuery = "SELECT * FROM products WHERE product_id = ? "
                + "AND deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(selectProductQuery)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = getProductFromResultSet(resultSet);
                    return Optional.of(product);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get product with ID " + id, e);
        }
    }

    @Override
    public List<Product> getAll() {
        String selectAllProductsQuery = "SELECT * FROM products WHERE deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(selectAllProductsQuery)) {
            statement.setBoolean(1, true);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Product> allProducts = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = getProductFromResultSet(resultSet);
                    allProducts.add(product);
                }
                return allProducts;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all products", e);
        }
    }

    @Override
    public Product update(Product product) {
        String updateProductQuery
                = "UPDATE products SET name = ?, price = ?, deleted = ? "
                + "WHERE product_id = ? AND deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(updateProductQuery)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + product, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String updateAvailabilityQuery
                = "UPDATE products SET deleted = ? WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(updateAvailabilityQuery)) {
            statement.setBoolean(1, false);
            statement.setLong(2, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to mark product deleted with ID "
                    + id + " as unavailable", e);
        }
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        Product product = new Product(id, name, price);
        product.setId(id);
        return product;
    }
}
