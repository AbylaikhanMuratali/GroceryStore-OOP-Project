import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add test data
        allProducts.add(new Product("P001", "Generic Item", 5.0, 100));
        allProducts.add(new FreshProduct("P002", "Apple", 2.0, 50, "2024-04-30", true));
        allProducts.add(new PackagedProduct("P003", "Chips", 3.0, 80, "Lays", 0.2));

        customers.add(new Customer("C001", "John", 200.0, "john@email.com"));
        sales.add(new Sale("S001", "John", 15.99, "2024-03-25", "Completed"));

        boolean running = true;

        while (running) {
            System.out.println("\n=== GROCERY STORE ===");
            System.out.println("1. Add Product");
            System.out.println("2. Add Fresh Product");
            System.out.println("3. Add Packaged Product");
            System.out.println("4. View All Products");
            System.out.println("5. Show Polymorphism");
            System.out.println("6. View Fresh Only");
            System.out.println("7. View Packaged Only");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: addFreshProduct(); break;
                case 3: addPackagedProduct(); break;
                case 4: viewAllProducts(); break;
                case 5: showPolymorphism(); break;
                case 6: viewFreshOnly(); break;
                case 7: viewPackagedOnly(); break;
                case 8: running = false; break;
            }
        }
        scanner.close();
    }

    private static void addProduct() {
        System.out.print("Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        allProducts.add(new Product(id, name, price, stock));
        System.out.println("Product added!");
    }

    private static void addFreshProduct() {
        System.out.print("Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Expiry Date (YYYY-MM-DD): ");
        String expiry = scanner.nextLine();
        System.out.print("Organic? (true/false): ");
        boolean organic = scanner.nextBoolean();
        scanner.nextLine();

        allProducts.add(new FreshProduct(id, name, price, stock, expiry, organic));
        System.out.println("Fresh product added!");
    }

    private static void addPackagedProduct() {
        System.out.print("Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Weight (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        allProducts.add(new PackagedProduct(id, name, price, stock, manufacturer, weight));
        System.out.println("Packaged product added!");
    }

    private static void viewAllProducts() {
        System.out.println("\n=== ALL PRODUCTS ===");
        if (allProducts.isEmpty()) {
            System.out.println("No products.");
            return;
        }

        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println((i+1) + ". " + allProducts.get(i));
        }
    }

    private static void showPolymorphism() {
        System.out.println("\n=== POLYMORPHISM DEMO ===");
        System.out.println("Calling toString() on all products:");

        for (Product p : allProducts) {
            System.out.println("- " + p);
        }

        System.out.println("\nSame toString() method, different output!");
    }

    private static void viewFreshOnly() {
        System.out.println("\n=== FRESH PRODUCTS ===");
        int count = 0;

        for (Product p : allProducts) {
            if (p instanceof FreshProduct) {
                count++;
                FreshProduct fresh = (FreshProduct) p;
                System.out.println(count + ". " + fresh.getName() + " - Expires: " + fresh.getExpiryDate());
            }
        }

        if (count == 0) {
            System.out.println("No fresh products.");
        }
    }

    private static void viewPackagedOnly() {
        System.out.println("\n=== PACKAGED PRODUCTS ===");
        int count = 0;

        for (Product p : allProducts) {
            if (p instanceof PackagedProduct) {
                count++;
                PackagedProduct pack = (PackagedProduct) p;
                System.out.println(count + ". " + pack.getName() + " - Manufacturer: " + pack.getManufacturer());
            }
        }

        if (count == 0) {
            System.out.println("No packaged products.");
        }
    }
}