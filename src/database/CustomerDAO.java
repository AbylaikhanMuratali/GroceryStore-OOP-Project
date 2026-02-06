package database;

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;  // ← ADD THIS LINE!
import java.util.Scanner;

public class CustomerDAO {

    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, name, total_purchases, email) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setDouble(3, customer.getTotalPurchases());
            statement.setString(4, customer.getEmail());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Customer saved to database!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        } finally {
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void getAllCustomers() {
        String sql = "SELECT * FROM customer ORDER BY customer_id";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            System.out.println("\n📋 === CUSTOMERS FROM DATABASE ===");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                System.out.println("ID: " + resultSet.getString("customer_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Total: " + resultSet.getDouble("total_purchases") + "tg");
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("---");
            }

            if (!found) {
                System.out.println("No customers in database yet.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Select failed: " + e.getMessage());
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean updateCustomer(String customerId, String newName, double newTotal, String newEmail) {
        Customer current = getCustomerById(customerId);
        if (current == null) {
            System.out.println("❌ Customer not found: " + customerId);
            return false;
        }

        String nameToUse = (newName == null || newName.trim().isEmpty()) ? current.getName() : newName;
        double totalToUse = (newTotal < 0) ? current.getTotalPurchases() : newTotal;
        String emailToUse = (newEmail == null || newEmail.trim().isEmpty()) ? current.getEmail() : newEmail;

        String sql = "UPDATE customer SET name = ?, total_purchases = ?, email = ? WHERE customer_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nameToUse);
            statement.setDouble(2, totalToUse);
            statement.setString(3, emailToUse);
            statement.setString(4, customerId);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Customer updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        } finally {
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("❌ Customer not found: " + customerId);
            return false;
        }

        System.out.println("\n⚠️  WILL DELETE:");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Total: " + customer.getTotalPurchases() + "tg");
        System.out.println("Email: " + customer.getEmail());
        System.out.print("Are you sure? (yes/no): ");

        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println("❌ Delete cancelled.");
            return false;
        }

        String sql = "DELETE FROM customer WHERE customer_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Customer deleted successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        } finally {
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Customer> searchByName(String searchName) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";
        // LOWER() makes it case-insensitive, % = wildcard

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchName + "%"); // Search anywhere in name

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("total_purchases"),
                        resultSet.getString("email")
                );
                results.add(customer);
            }

            System.out.println("🔍 Found " + results.size() + " customer(s) matching '" + searchName + "'");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return results;
    }

    public List<Customer> searchByPurchaseRange(double min, double max) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE total_purchases BETWEEN ? AND ? ORDER BY total_purchases DESC";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, min);
            statement.setDouble(2, max);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("total_purchases"),
                        resultSet.getString("email")
                );
                results.add(customer);
            }

            System.out.println("💰 Found " + results.size() + " customer(s) spending between " + min + "tg and " + max + "tg");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return results;
    }

    public List<Customer> searchByMinPurchase(double minPurchase) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE total_purchases >= ? ORDER BY total_purchases DESC";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPurchase);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("total_purchases"),
                        resultSet.getString("email")
                );
                results.add(customer);
            }

            System.out.println("💰 Found " + results.size() + " customer(s) spending at least " + minPurchase + "tg");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return results;
    }

    private Customer getCustomerById(String customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("total_purchases"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error getting customer: " + e.getMessage());
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    public void displayCustomerList(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers to display.");
            return;
        }

        System.out.println("\n=== SEARCH RESULTS ===");
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i+1) + ". ID: " + c.getCustomerId() +
                    " | Name: " + c.getName() +
                    " | Total: " + c.getTotalPurchases() + "tg" +
                    " | Email: " + c.getEmail());
        }
    }
}