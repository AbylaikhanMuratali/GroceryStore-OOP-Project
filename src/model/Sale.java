package model;

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
        if (saleId == null || saleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Sale ID cannot be empty!");
        }
        this.saleId = saleId;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty!");
        }
        this.customerName = customerName;
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative!");
        }
        this.totalAmount = totalAmount;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty!");
        }
        this.date = date;
    }

    public void setStatus(String status) {
        if (status == null || (!status.equals("Pending") && !status.equals("Completed"))) {
            throw new IllegalArgumentException("Status must be 'Pending' or 'Completed'!");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale #" + saleId + " - " + customerName + " - " + totalAmount + "tg";
    }
}