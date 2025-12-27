public class Main {
    public static void main(String[] args) {

        System.out.println("=== GROCERY STORE MANAGEMENT ===");
        System.out.println();

        // objects
        Product apple = new Product("P100", "Apple", 1.99, 100);
        Product milk = new Product("P101", "Milk", 3.49, 50);
        Product bread = new Product("P102", "Bread", 2.99, 40);

        Customer john = new Customer("C100", "John", 200.0, "john@email.com");
        Customer jane = new Customer("C101", "Jane", 600.0, "jane@email.com");

        Sale sale1 = new Sale("S100", "John", 0.0, "2024-03-25", "Pending");
        Sale sale2 = new Sale("S101", "Jane", 0.0, "2024-03-26", "Pending");

        // show objects
        System.out.println("--- ALL OBJECTS ---");
        System.out.println(apple);
        System.out.println(milk);
        System.out.println(bread);
        System.out.println(john);
        System.out.println(jane);
        System.out.println(sale1);
        System.out.println(sale2);
        System.out.println();

        // test getters
        System.out.println("--- GETTERS ---");
        System.out.println("Apple price: " + apple.getPrice() + "tg");
        System.out.println("Milk stock: " + milk.getStockQuantity());
        System.out.println("John's email: " + john.getEmail());
        System.out.println("Sale 1 date: " + sale1.getDate());
        System.out.println();

        // test settes
        System.out.println("--- SETTERS ---");
        apple.setPrice(2.49);
        john.setName("John Smith");
        sale1.setDate("2024-03-27");
        System.out.println("Updated apple: " + apple);
        System.out.println("Updated john: " + john);
        System.out.println("Updated sale1: " + sale1);
        System.out.println();

        // test methods
        System.out.println("--- METHODS ---");

        // product method
        System.out.println("Is bread in stock? " + bread.isInStock());
        milk.applyDiscount(10);
        System.out.println("Milk after 10% discount: " + milk.getPrice() + "tg");

        // customer methods
        john.addPurchase(100.0);
        System.out.println("Is John VIP? " + john.isVIP());
        System.out.println("Is Jane VIP? " + jane.isVIP());

        // sale methods
        sale1.addItem(apple.getPrice());
        sale1.addItem(milk.getPrice());
        sale1.completeSale();

        sale2.addItem(15.0);
        sale2.addItem(8.5);

        // 7. final output
        System.out.println();
        System.out.println("--- FINAL STATE ---");
        System.out.println(apple);
        System.out.println(milk);
        System.out.println(bread);
        System.out.println(john);
        System.out.println(jane);
        System.out.println(sale1);
        System.out.println(sale2);

        System.out.println();
        System.out.println("=== PROGRAM COMPLETE ===");
    }
}