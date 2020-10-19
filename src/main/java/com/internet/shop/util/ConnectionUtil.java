package com.internet.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String USER = "q7uf413za7c8n61w";
    private static final String PASSWORD = "rc9rlsjjon2ydn8n";
    private static final String URL = "jdbc:mysql://f2fbe0zvg9j8p9ng.cbetxkdyhwsb" 
            + ".us-east-1.rds.amazonaws.com/qnot3qm6qb4x5p2x";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Can not find MySQL Driver", e);
        }
    }

    private ConnectionUtil() {

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("Can not establish connection to DB", e);
        }
    }
}
