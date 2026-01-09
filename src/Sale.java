public class Sale {
    private String saleId;
    private String customerName;
    private double totalAmount;
    private String date;
    private String status;

    public Sale(String saleId, String customerName, double totalAmount, String date, String status) {
        setSaleId(saleId);
        setCustomerName(customerName);
        setTotalAmount(totalAmount);
        setDate(date);
        setStatus(status);
    }

    public String getSaleId() { return saleId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public void setSaleId(String saleId) {
        if (saleId != null && !saleId.trim().isEmpty()) {
            this.saleId = saleId;
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        }
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        }
    }

    public void setDate(String date) {
        if (date != null && !date.trim().isEmpty()) {
            this.date = date;
        }
    }

    public void setStatus(String status) {
        if (status != null && (status.equals("Pending") || status.equals("Completed"))) {
            this.status = status;
        }
    }

    public void addItem(double price) {
        if (price > 0) {
            totalAmount += price;
        }
    }

    public void completeSale() {
        status = "Completed";
    }

    @Override
    public String toString() {
        return "Sale #" + saleId + " - " + customerName + " - " + totalAmount + "tg - " + status;
    }
}