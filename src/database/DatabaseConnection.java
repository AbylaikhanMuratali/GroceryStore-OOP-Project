package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/grocery_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Abylai0102"; // CHANGE THIS IF WRONG

    public static Connection getConnection() {
        try {
            // MANUALLY LOAD THE DRIVER FIRST
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("⚠️ DRIVER ERROR: PostgreSQL JDBC Driver not found!");
                System.out.println("   SOLUTION: Download postgresql-42.7.3.jar from:");
                System.out.println("   https://jdbc.postgresql.org/download/");
                System.out.println("   And add it to your project's lib folder");
                return null;
            }

            // Try to connect
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
            return conn;

        } catch (SQLException e) {
            System.out.println("❌ DATABASE ERROR: " + e.getMessage());

            // Show specific solutions based on error
            if (e.getMessage().contains("Connection refused")) {
                System.out.println("   🔧 FIX: Start PostgreSQL service");
                System.out.println("   Windows: Open Services -> Start PostgreSQL");
                System.out.println("   Linux/Mac: sudo service postgresql start");
            }

            if (e.getMessage().contains("password authentication")) {
                System.out.println("   🔧 FIX: Wrong password! Check line 9 in DatabaseConnection.java");
            }

            if (e.getMessage().contains("grocery_db")) {
                System.out.println("   🔧 FIX: Create database: CREATE DATABASE grocery_db;");
            }

            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Ignore close errors
            }
        }
    }

    // TEST METHOD - Run this separately
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("SUCCESS! Database is working.");
            closeConnection(conn);
        } else {
            System.out.println("FAILED! Check the error messages above.");
        }
    }
}