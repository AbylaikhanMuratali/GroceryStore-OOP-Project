public class Customer {
    // 4 FIELDS
    private String customerId;
    private String name;
    private double totalPurchases;
    private String email;

    // CONSTRUCTOR WITH 4 PARAMETERS
    public Customer(String customerId, String name, double totalPurchases, String email) {
        this.customerId = customerId;
        this.name = name;
        this.totalPurchases = totalPurchases;
        this.email = email;
    }

    // GETTERS for all 4
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public double getTotalPurchases() { return totalPurchases; }
    public String getEmail() { return email; }

    // SETTERS for all 4
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public void setTotalPurchases(double totalPurchases) { this.totalPurchases = totalPurchases; }
    public void setEmail(String email) { this.email = email; }

    // 2 METHODS
    public void addPurchase(double amount) {
        totalPurchases = totalPurchases + amount;
    }

    public boolean isVIP() {
        return totalPurchases > 500;
    }

    // toString()
    public String toString() {
        return "Customer[ID=" + customerId + ", Name=" + name +
                ", Total=$" + totalPurchases + ", Email=" + email + "]";
    }
}