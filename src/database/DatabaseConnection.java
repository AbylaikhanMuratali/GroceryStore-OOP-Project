package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // CHANGE THIS PASSWORD TO YOUR POSTGRES PASSWORD!
    private static final String URL = "jdbc:postgresql://localhost:5432/grocery_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Abylai0102"; // CHANGE THIS!

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to database!");
            return connection;
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔌 Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Add to DatabaseConnection.java
    public static void test() {
        System.out.println("Testing connection to: " + URL);
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✅ Connection SUCCESS!");
            closeConnection(conn);
        } else {
            System.out.println("❌ Connection FAILED!");
            System.out.println("Check: 1. PostgreSQL running, 2. Password correct");
        }
    }
}