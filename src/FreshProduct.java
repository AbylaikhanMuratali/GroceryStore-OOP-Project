public class FreshProduct extends Product {
    private String expiryDate;
    private boolean isOrganic;

    public FreshProduct(String productId, String name, double price, int stock,
                        String expiryDate, boolean isOrganic) {
        super(productId, name, price, stock);
        this.expiryDate = expiryDate;
        this.isOrganic = isOrganic;
    }

    @Override
    public String getProductType() {
        return "Fresh Product";
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    @Override
    public String toString() {
        String organic = isOrganic ? "Organic" : "Not Organic";
        return super.toString() + " - Expires: " + expiryDate + " - " + organic;
    }
}