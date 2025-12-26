public class Customer {
    private String customerId;
    private String name;
    private double totalPurchases;
    private String email;

    // Constructor
    public Customer(String customerId, String name, double totalPurchases, String email) {
        this.customerId = customerId;
        this.name = name;
        this.totalPurchases = totalPurchases;
        this.email = email;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public double getTotalPurchases() { return totalPurchases; }
    public String getEmail() { return email; }

    // Setters
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public void setTotalPurchases(double totalPurchases) { this.totalPurchases = totalPurchases; }
    public void setEmail(String email) { this.email = email; }

    // 2 Methods
    public void addPurchase(double amount) {
        totalPurchases = totalPurchases + amount;
    }

    public boolean isVIP() {
        return totalPurchases > 500;
    }

    // toString()
    public String toString() {
        return "Customer: " + name + " (" + customerId + ") - Spent: " + totalPurchases + "tg - Email: " + email;
    }
}