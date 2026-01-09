public class PackagedProduct extends Product {
    private String manufacturer;
    private double weight;

    public PackagedProduct(String productId, String name, double price, int stock,
                           String manufacturer, double weight) {
        super(productId, name, price, stock);
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    @Override
    public String getProductType() {
        return "Packaged Product";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return super.toString() + " - Made by: " + manufacturer + " - Weight: " + weight + "kg";
    }
}