public class Main {
    public static void main(String[] args) {
        System.out.println("=== GROCERY STORE ===\n");

        // CREATE OBJECTS WITH CORRECT CONSTRUCTORS

        // Product: 4 parameters
        Product p1 = new Product("P001", "Apple", 1.99, 100);
        Product p2 = new Product("P002", "Milk", 3.49, 50);

        // Customer: 4 parameters  
        Customer c1 = new Customer("C001", "John", 200.0, "john@email.com");
        Customer c2 = new Customer("C002", "Jane", 600.0, "jane@email.com");

        // Sale: 5 parameters (includes date)
        Sale s1 = new Sale("S001", "John", 0.0, "2024-03-25", "Pending");

        // SHOW OBJECTS
        System.out.println("--- OBJECTS ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(s1);

        // TEST GETTERS
        System.out.println("\n--- GETTERS ---");
        System.out.println("Product 1 Name: " + p1.getName());
        System.out.println("Product 1 Price: $" + p1.getPrice());
        System.out.println("Customer 1 Email: " + c1.getEmail());
        System.out.println("Sale 1 Date: " + s1.getDate());  // Testing date getter
        System.out.println("Sale 1 Status: " + s1.getStatus());

        // TEST SETTERS
        System.out.println("\n--- SETTERS ---");
        p1.setPrice(2.49);
        c1.setName("John Smith");
        s1.setDate("2024-03-26");  // Testing date setter
        s1.setStatus("Processing");

        System.out.println("Updated Product 1: " + p1);
        System.out.println("Updated Sale 1: " + s1);

        // TEST METHODS
        System.out.println("\n--- METHODS ---");

        // Product methods
        System.out.println("Is Apple in stock? " + p1.isInStock());
        p1.applyDiscount(15);
        System.out.println("Apple price after 15% discount: $" + p1.getPrice());

        // Customer methods
        c1.addPurchase(100.0);
        System.out.println("Is John VIP? " + c1.isVIP());

        // Sale methods
        s1.addItem(10.0);
        s1.addItem(5.0);
        s1.completeSale();

        // FINAL STATE
        System.out.println("\n--- FINAL ---");
        System.out.println(p1);
        System.out.println(c1);
        System.out.println(s1);

        System.out.println("\n=== DONE ===");
    }
}