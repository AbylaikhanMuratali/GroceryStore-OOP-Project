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
        if (customerId != null && !customerId.trim().isEmpty()) {
            this.customerId = customerId;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases >= 0) {
            this.totalPurchases = totalPurchases;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void addPurchase(double amount) {
        if (amount > 0) {
            totalPurchases += amount;
        }
    }

    public boolean isVIP() {
        return totalPurchases > 500;
    }

    @Override
    public String toString() {
        return name + " - Spent: " + totalPurchases + "tg";
    }
}