package model;

public class FreshProduct extends Product implements Sellable {
    private String expiryDate;
    private boolean isOrganic;

    public FreshProduct(String productId, String name, double price, int stock,
                        String expiryDate, boolean isOrganic) {
        super(productId, name, price, stock);
        setExpiryDate(expiryDate);
        this.isOrganic = isOrganic;
    }

    @Override
    public String getProductInfo() {
        return "Fresh: " + getName() + " (Expires: " + expiryDate + ")";
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.12;
    }

    @Override
    public boolean isAvailable() {
        return getStock() > 0;
    }

    @Override
    public void sellItem() {
        System.out.println("Sold: " + getName());
    }

    public String getExpiryDate() { return expiryDate; }
    public boolean isOrganic() { return isOrganic; }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be empty!");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.toString() + " [Fresh]";
    }
}