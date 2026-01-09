public class Product {
    protected String productId;
    protected String name;
    protected double price;
    protected int stock;

    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public String getProductType() {
        return "General Product";
    }

    public void applyDiscount(double percent) {
        if (percent > 0 && percent <= 100) {
            price = price - (price * percent / 100);
        }
    }

    @Override
    public String toString() {
        return "[" + getProductType() + "] " + name + " - " + price + "tg";
    }
}