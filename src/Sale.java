public class Sale{
    private String saleId;
    private String customerName;
    private double totalAmount;
    private String date;
    private String status;

//constructr
    public Sale(String saleId, String customerName, double totalAmount, String date, String status) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
    }

    //getters
    public String getSaleId() {return saleId;}
    public String getCustomerName() {return customerName;}
    public double getTotalAmount() {return totalAmount;}
    public String getDate() {return date;}
    public String getStatus() {return status;}


    //setters
    public void setSaleId(String saleId) {this.saleId = saleId;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}
    public void setDate(String date) {this.date = date;}
    public void setStatus(String status) {this.status = status;}


    //methid 1
    public void addItem(double price) {
        totalAmount = totalAmount + price;
    }

    public void completeSale() {
        status = "Completed";
    }

    //method 2
    public String toString() {
        return "Sale[ID=" + saleId +", Customer="+ customerName +", Total=$" + totalAmount +", Date=" + date+", Status="+ status +"]";
    }
}