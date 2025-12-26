public class Sale {
    private String saleId;
    private String customerName;
    private double totalAmount;
    private String date;
    private String status;

    // Constructor
    public Sale(String saleId, String customerName, double totalAmount, String date, String status) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
    }

    // Getters
    public String getSaleId() { return saleId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    // Setters
    public void setSaleId(String saleId) { this.saleId = saleId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }

    // 2 Methods
    public void addItem(double price) {
        totalAmount = totalAmount + price;
    }

    public void completeSale() {
        status = "Completed";
    }

    // toString()
    public String toString() {
        return "Sale " + saleId + ": " + customerName + " - " + totalAmount + "tg - Date: " + date + " - Status: " + status;
    }
}