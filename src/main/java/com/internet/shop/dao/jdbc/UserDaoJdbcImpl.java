package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public User create(User user) {
        String insertUserQuery
                = "INSERT INTO users (name, login, password, salt) VALUES (?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement
                        = connection.prepareStatement(insertUserQuery,
                         Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                    statement.close();
                    insertUsersRoles(user, connection);
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create " + user, e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String selectUserQuery = "SELECT * FROM users WHERE login = ? "
                + "AND deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(selectUserQuery)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                statement.close();
                user.getRoles().addAll(getRolesFromUserId(user.getId(), connection));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get user with login " + login, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String selectUserQuery = "SELECT * FROM users WHERE user_id = ? "
                + "AND deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(selectUserQuery)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = getUserFromResultSet(resultSet);
                    statement.close();
                    user.getRoles().addAll(getRolesFromUserId(user.getId(), connection));
                    return Optional.of(user);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get user with ID " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        String selectAllUsersQuery = "SELECT * FROM users WHERE deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(selectAllUsersQuery);
                 ResultSet resultSet = statement.executeQuery()) {
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                allUsers.add(user);
            }
            statement.close();
            for (User user : allUsers) {
                if (user != null) {
                    user.getRoles().addAll(getRolesFromUserId(user.getId(), connection));
                }
            }
            return allUsers;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to retrieve all users", e);
        }
    }

    @Override
    public User update(User element) {
        String updateUserQuery = "UPDATE users SET name = ?, login = ?, password = ?, salt = ?"
                + "WHERE user_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(updateUserQuery)) {
            statement.setString(1, element.getName());
            statement.setString(2, element.getLogin());
            statement.setString(3, element.getPassword());
            statement.setBytes(4, element.getSalt());
            statement.setLong(5, element.getId());
            statement.executeUpdate();
            statement.close();
            deleteUserFromUsersRoles(element.getId(), connection);
            insertUsersRoles(element, connection);
            return element;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update " + element, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String deleteUserQuery = "UPDATE users SET deleted = TRUE WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(deleteUserQuery)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete user with ID " + id, e);
        }
    }

    private void insertUsersRoles(User user, Connection connection) throws SQLException {
        String insertUsersRolesQuery = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?,(SELECT role_id FROM roles WHERE role_name = ?))";
        try (PreparedStatement statement =
                     connection.prepareStatement(insertUsersRolesQuery)) {
            for (Role role : user.getRoles()) {
                statement.setLong(1, user.getId());
                statement.setString(2, role.getRoleName().name());
                statement.executeUpdate();
            }
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        User user = new User(name, login, password);
        user.setId(id);
        user.setSalt(salt);
        return user;
    }

    private Set<Role> getRolesFromUserId(Long userId, Connection connection) throws SQLException {
        String selectRoleNameQuery = "SELECT role_name FROM users_roles "
                + "JOIN roles USING (role_id) WHERE user_id = ?;";
        try (PreparedStatement statement
                     = connection.prepareStatement(selectRoleNameQuery)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Set<Role> roles = new HashSet<>();
                while (resultSet.next()) {
                    roles.add(Role.of(resultSet.getString("role_name")));
                }
                return roles;
            }
        }
    }

    private void deleteUserFromUsersRoles(Long userId, Connection connection) throws SQLException {
        String deleteUserQuery = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement statement
                     = connection.prepareStatement(deleteUserQuery)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        }
    }
}
