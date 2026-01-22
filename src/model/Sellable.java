package model;

public interface Sellable {
    double calculateTax();
    boolean isAvailable();
    void sellItem();
}