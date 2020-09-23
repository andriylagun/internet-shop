package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
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
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        String insertOrderQuery = "INSERT INTO orders (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(insertOrderQuery,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                order.setId(resultSet.getLong(1));
                statement.close();
                insertOrdersProducts(order, connection);
                return order;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + order, e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String selectOrderQuery = "SELECT * FROM orders WHERE order_id = ? "
                + "AND deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement
                        = connection.prepareStatement(selectOrderQuery)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = getOrderFromResultSet(resultSet, connection);
                    return Optional.of(order);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get order with ID " + id, e);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        String selectAllOrdersQuery = "SELECT * FROM orders WHERE user_id = ? "
                + "AND deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(selectAllOrdersQuery)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> allOrders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet, connection);
                allOrders.add(order);
            }
            return allOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all orders of user with ID "
                    + userId, e);
        }
    }

    @Override
    public List<Order> getAll() {
        String selectAllOrdersQuery = "SELECT * FROM orders WHERE deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(selectAllOrdersQuery);
                ResultSet resultSet = statement.executeQuery()) {
            List<Order> allOrders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet, connection);
                allOrders.add(order);
            }
            return allOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all orders", e);
        }
    }

    @Override
    public Order update(Order order) {
        String updateOrderQuery = "UPDATE orders SET user_id = ? WHERE order_id = ? "
                + "AND deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(updateOrderQuery)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
            deleteOrderFromOrdersProducts(order.getId(), connection);
            insertOrdersProducts(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + order, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteOrderQuery = "UPDATE orders SET deleted = TRUE WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(deleteOrderQuery)) {
            deleteOrderFromOrdersProducts(id,connection);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete order with ID " + id, e);
        }
    }

    private void insertOrdersProducts(Order order, Connection connection) throws SQLException {
        String insertOrdersProductsQuery
                = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?);";
        try (PreparedStatement insertStatement =
                     connection.prepareStatement(insertOrdersProductsQuery)) {
            for (Product product : order.getProducts()) {
                insertStatement.setLong(1, order.getId());
                insertStatement.setLong(2, product.getId());
                insertStatement.executeUpdate();
            }
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet, Connection connection)
            throws SQLException {
        Long id = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        Order order = new Order(getProductsFromOrderId(id, connection), userId);
        order.setId(id);
        return order;
    }

    private List<Product> getProductsFromOrderId(Long orderId, Connection connection)
            throws SQLException {
        String selectProductIdQuery = "SELECT products.* FROM orders_products "
                + "JOIN products USING (product_id) WHERE order_id = ?;";
        try (PreparedStatement statement =
                     connection.prepareStatement(selectProductIdQuery)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("product_id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    boolean available = resultSet.getBoolean("available");
                    Product product = new Product(name, price, available);
                    product.setId(id);
                    products.add(product);
                }
                return products;
            }
        }
    }

    private void deleteOrderFromOrdersProducts(Long orderId, Connection connection)
            throws SQLException {
        String deleteOrderQuery = "DELETE FROM orders_products WHERE order_id = ?;";
        try (PreparedStatement statement =
                     connection.prepareStatement(deleteOrderQuery)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        }
    }
}
