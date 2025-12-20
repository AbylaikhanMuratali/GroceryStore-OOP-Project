public class Product {
    // 4 FIELDS
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    // CONSTRUCTOR WITH 4 PARAMETERS
    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // GETTERS for all 4 fields
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    // SETTERS for all 4 fields
    public void setProductId(String productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    // 2 METHODS
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void applyDiscount(double percent) {
        double discount = price * (percent / 100);
        price = price - discount;
    }

    // toString()
    public String toString() {
        return "Product[ID=" + productId + ", Name=" + name +
                ", Price=$" + price + ", Stock=" + stockQuantity + "]";
    }
}