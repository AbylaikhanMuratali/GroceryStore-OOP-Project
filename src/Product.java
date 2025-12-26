public class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    // Constructor
    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setProductId(String productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    // 2 Methods
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void applyDiscount(double percent) {
        double discount = price * (percent / 100);
        price = price - discount;
    }

    // toString()
    public String toString() {
        return "Product: " + name + " (" + productId + ") - " + price + "tg - Stock: " + stockQuantity;
    }
}