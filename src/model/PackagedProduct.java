package model;

public class PackagedProduct extends Product {
    private String manufacturer;
    private double weight;

    public PackagedProduct(String productId, String name, double price, int stock,
                           String manufacturer, double weight) {
        super(productId, name, price, stock);
        setManufacturer(manufacturer);
        setWeight(weight);
    }

    @Override
    public String getProductInfo() {
        return "Packaged: " + getName() + " (Made by: " + manufacturer + ")";
    }

    public String getManufacturer() { return manufacturer; }
    public double getWeight() { return weight; }

    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty!");
        }
        this.manufacturer = manufacturer;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive!");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() + " [Packaged]";
    }
}