package menu;

import model.*;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Sale> sales;
    private Scanner scanner;

    public MenuManager() {
        products = new ArrayList<>();
        customers = new ArrayList<>();
        sales = new ArrayList<>();
        scanner = new Scanner(System.in);
        addTestData();
    }

    private void addTestData() {
        try {
            Product general = new Product("P001", "Generic", 10.0, 100) {
                @Override
                public String getProductInfo() {
                    return "General product";
                }
            };
            products.add(general);

            products.add(new FreshProduct("P002", "Apple", 2.0, 50, "2024-04-30", true));
            products.add(new PackagedProduct("P003", "Chips", 3.0, 80, "Lays", 0.2));

            customers.add(new Customer("C001", "John", 200.0, "john@email.com"));
            sales.add(new Sale("S001", "John", 15.99, "2024-03-25", "Completed"));

        } catch (Exception e) {
            System.out.println("Test data error: " + e.getMessage());
        }
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== GROCERY STORE ===");
        System.out.println("1. View Products");
        System.out.println("2. Add Fresh Product");
        System.out.println("3. Add Packaged Product");
        System.out.println("4. Show Abstract Method");
        System.out.println("5. Test Sellable Interface");
        System.out.println("6. Add Customer");
        System.out.println("7. View Customers");
        System.out.println("8. Add Sale");
        System.out.println("9. View Sales");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void startMenu() {
        boolean running = true;

        while (running) {
            showMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: viewProducts(); break;
                    case 2: addFreshProduct(); break;
                    case 3: addPackagedProduct(); break;
                    case 4: showAbstractMethod(); break;
                    case 5: testSellable(); break;
                    case 6: addCustomer(); break;
                    case 7: viewCustomers(); break;
                    case 8: addSale(); break;
                    case 9: viewSales(); break;
                    case 0:
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a number!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (running && !scanner.hasNextLine()) {
                System.out.println("\nPress Enter...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void viewProducts() {
        System.out.println("\n=== PRODUCTS ===");
        if (products.isEmpty()) {
            System.out.println("No products.");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i));
        }
    }

    private void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT ---");

        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Expiry (YYYY-MM-DD): ");
            String expiry = scanner.nextLine();

            System.out.print("Organic? (true/false): ");
            boolean organic = Boolean.parseBoolean(scanner.nextLine());

            FreshProduct fresh = new FreshProduct(id, name, price, stock, expiry, organic);
            products.add(fresh);
            System.out.println("Added!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number!");
        }
    }

    private void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT ---");

        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Manufacturer: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Weight (kg): ");
            double weight = Double.parseDouble(scanner.nextLine());

            PackagedProduct packaged = new PackagedProduct(id, name, price, stock, manufacturer, weight);
            products.add(packaged);
            System.out.println("Added!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number!");
        }
    }

    private void showAbstractMethod() {
        System.out.println("\n=== ABSTRACT METHOD ===");

        for (Product p : products) {
            System.out.println(p.getProductInfo());
        }
    }

    private void testSellable() {
        System.out.println("\n=== SELLABLE INTERFACE ===");

        for (Product p : products) {
            if (p instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) p;
                System.out.println("Product: " + fresh.getName());
                System.out.println("Tax: " + fresh.calculateTax() + "tg");
                System.out.println("Available: " + fresh.isAvailable());
                fresh.sellItem();
                return;
            }
        }

        System.out.println("No FreshProduct found!");
    }

    private void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Total Purchases: ");
            double total = Double.parseDouble(scanner.nextLine());

            System.out.print("Email: ");
            String email = scanner.nextLine();

            Customer customer = new Customer(id, name, total, email);
            customers.add(customer);
            System.out.println("Added!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number!");
        }
    }

    private void viewCustomers() {
        System.out.println("\n=== CUSTOMERS ===");
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }

        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i+1) + ". " + customers.get(i));
        }
    }

    private void addSale() {
        System.out.println("\n--- ADD SALE ---");

        try {
            System.out.print("Sale ID: ");
            String id = scanner.nextLine();

            System.out.print("Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Total Amount: ");
            double total = Double.parseDouble(scanner.nextLine());

            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Status (Pending/Completed): ");
            String status = scanner.nextLine();

            Sale sale = new Sale(id, name, total, date, status);
            sales.add(sale);
            System.out.println("Added!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number!");
        }
    }

    private void viewSales() {
        System.out.println("\n=== SALES ===");
        if (sales.isEmpty()) {
            System.out.println("No sales.");
            return;
        }

        for (int i = 0; i < sales.size(); i++) {
            System.out.println((i+1) + ". " + sales.get(i));
        }
    }
}