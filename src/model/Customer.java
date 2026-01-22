package model;

public class Customer {
    private String customerId;
    private String name;
    private double totalPurchases;
    private String email;

    public Customer(String customerId, String name, double totalPurchases, String email) {
        setCustomerId(customerId);
        setName(name);
        setTotalPurchases(totalPurchases);
        setEmail(email);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public double getTotalPurchases() { return totalPurchases; }
    public String getEmail() { return email; }

    public void setCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty!");
        }
        this.customerId = customerId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty!");
        }
        this.name = name;
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases < 0) {
            throw new IllegalArgumentException("Total purchases cannot be negative!");
        }
        this.totalPurchases = totalPurchases;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @!");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return getName() + " - Spent: " + totalPurchases + "tg";
    }
}