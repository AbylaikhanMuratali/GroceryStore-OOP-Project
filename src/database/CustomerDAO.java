package database;

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDAO {

    // 1. INSERT - 14 lines (Same functionality)
    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getCustomerId());
            stmt.setString(2, customer.getName());
            stmt.setDouble(3, customer.getTotalPurchases());
            stmt.setString(4, customer.getEmail());

            stmt.executeUpdate();
            System.out.println("✅ Customer saved!");

        } catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
        }
    }

    // 2. GET ALL - 18 lines
    public void getAllCustomers() {
        String sql = "SELECT * FROM customer ORDER BY customer_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📋 ALL CUSTOMERS:");

            while (rs.next()) {
                System.out.println("ID: " + rs.getString("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Total: " + rs.getDouble("total_purchases") + "tg");
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---");
            }

        } catch (SQLException e) {
            System.out.println("❌ Select failed: " + e.getMessage());
        }
    }

    // 3. UPDATE - 16 lines (with null checks simplified)
    public boolean updateCustomer(String id, String newName, double newTotal, String newEmail) {
        Customer current = getCustomerById(id);
        if (current == null) {
            System.out.println("❌ Customer not found!");
            return false;
        }

        // Use new values or keep old ones
        String name = (newName.isEmpty()) ? current.getName() : newName;
        double total = (newTotal < 0) ? current.getTotalPurchases() : newTotal;
        String email = (newEmail.isEmpty()) ? current.getEmail() : newEmail;

        String sql = "UPDATE customer SET name=?, total_purchases=?, email=? WHERE customer_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setDouble(2, total);
            stmt.setString(3, email);
            stmt.setString(4, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Customer updated!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
        }
        return false;
    }

    // 4. DELETE - 22 lines (with confirmation)
    public boolean deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("❌ Customer not found!");
            return false;
        }

        System.out.println("\n⚠️ WILL DELETE:");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.print("Are you sure? (yes/no): ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("❌ Delete cancelled");
            return false;
        }

        String sql = "DELETE FROM customer WHERE customer_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Customer deleted!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
        }
        return false;
    }

    // 5. SEARCH BY NAME - 15 lines
    public List<Customer> searchByName(String searchName) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchName + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getDouble("total_purchases"),
                        rs.getString("email")
                ));
            }

            System.out.println("🔍 Found " + results.size() + " customer(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        }
        return results;
    }

    // 6. SEARCH BY PURCHASE RANGE - 16 lines
    public List<Customer> searchByPurchaseRange(double min, double max) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE total_purchases BETWEEN ? AND ? ORDER BY total_purchases DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getDouble("total_purchases"),
                        rs.getString("email")
                ));
            }

            System.out.println("💰 Found " + results.size() + " customer(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        }
        return results;
    }

    // 7. SEARCH BY MIN PURCHASE - 15 lines
    public List<Customer> searchByMinPurchase(double minPurchase) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE total_purchases >= ? ORDER BY total_purchases DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, minPurchase);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getDouble("total_purchases"),
                        rs.getString("email")
                ));
            }

            System.out.println("💰 Found " + results.size() + " customer(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        }
        return results;
    }

    // 8. GET CUSTOMER BY ID (private) - 14 lines
    private Customer getCustomerById(String customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getDouble("total_purchases"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // 9. DISPLAY LIST - 10 lines
    public void displayCustomerList(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers to display.");
            return;
        }

        System.out.println("\n=== RESULTS ===");
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i+1) + ". ID: " + c.getCustomerId() +
                    " | Name: " + c.getName() +
                    " | Total: " + c.getTotalPurchases() + "tg" +
                    " | Email: " + c.getEmail());
        }
    }
}