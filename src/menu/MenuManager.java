package menu;

import model.*;
import database.CustomerDAO;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class MenuManager implements Menu {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Sale> sales;
    private Scanner scanner;
    private CustomerDAO customerDAO;

    public MenuManager() {
        products = new ArrayList<>();
        customers = new ArrayList<>();
        sales = new ArrayList<>();
        scanner = new Scanner(System.in);
        customerDAO = new CustomerDAO();
        addTestData();
    }

    private void addTestData() {
        products.add(new FreshProduct("P001", "Apple", 2.0, 50, "2024-04-30", true));
        products.add(new PackagedProduct("P002", "Chips", 3.0, 80, "Lays", 0.2));
        customers.add(new Customer("C001", "John", 200.0, "john@email.com"));
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
        System.out.println("7. View Customers (Memory)");
        System.out.println("8. Add Sale");
        System.out.println("9. View Sales");
        System.out.println("10. View Customers (Database)");
        System.out.println("11. Update Customer");
        System.out.println("12. Delete Customer");
        System.out.println("13. Search Customer by Name");
        System.out.println("14. Search by Purchase Range");
        System.out.println("15. Search High Spenders");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void startMenu() {
        while (true) {
            showMenu();
            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                handleChoice(choice);
            } catch (Exception e) {
                System.out.println("Invalid choice!");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

    private void handleChoice(int choice) {
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
            case 10: viewCustomersFromDatabase(); break;
            case 11: updateCustomer(); break;
            case 12: deleteCustomer(); break;
            case 13: searchCustomerByName(); break;
            case 14: searchByPurchaseRange(); break;
            case 15: searchHighSpenders(); break;
            default: System.out.println("Invalid choice!");
        }
    }

    // 1. VIEW PRODUCTS - 10 lines
    private void viewProducts() {
        System.out.println("\n=== PRODUCTS ===");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i));
        }
    }

    // 2. ADD FRESH PRODUCT - 12 lines
    private void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT ---");

        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Price: "); double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Stock: "); int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Expiry (YYYY-MM-DD): "); String expiry = scanner.nextLine();
        System.out.print("Organic? (true/false): "); boolean organic = scanner.nextBoolean();
        scanner.nextLine();

        products.add(new FreshProduct(id, name, price, stock, expiry, organic));
        System.out.println("✅ Added!");
    }

    // 3. ADD PACKAGED PRODUCT - 12 lines
    private void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT ---");

        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Price: "); double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Stock: "); int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Manufacturer: "); String manufacturer = scanner.nextLine();
        System.out.print("Weight (kg): "); double weight = scanner.nextDouble();
        scanner.nextLine();

        products.add(new PackagedProduct(id, name, price, stock, manufacturer, weight));
        System.out.println("✅ Added!");
    }

    // 4. SHOW ABSTRACT METHOD - 5 lines
    private void showAbstractMethod() {
        System.out.println("\n=== ABSTRACT METHOD ===");
        for (Product p : products) {
            System.out.println(p.getProductInfo());
        }
    }

    // 5. TEST SELLABLE INTERFACE - 7 lines
    private void testSellable() {
        System.out.println("\n=== SELLABLE INTERFACE ===");
        for (Product p : products) {
            if (p instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) p;
                System.out.println("Tax: " + fresh.calculateTax() + "tg");
                System.out.println("Available: " + fresh.isAvailable());
                break;
            }
        }
    }

    // 6. ADD CUSTOMER - 15 lines
    private void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Total Purchases: "); double total = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();

        Customer customer = new Customer(id, name, total, email);
        customers.add(customer);
        customerDAO.insertCustomer(customer);
        System.out.println("✅ Saved to memory and database!");
    }

    // 7. VIEW CUSTOMERS (MEMORY) - 8 lines
    private void viewCustomers() {
        System.out.println("\n=== CUSTOMERS (MEMORY) ===");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i+1) + ". " + customers.get(i));
        }
    }

    // 8. ADD SALE - 12 lines
    private void addSale() {
        System.out.println("\n--- ADD SALE ---");

        System.out.print("Sale ID: "); String id = scanner.nextLine();
        System.out.print("Customer Name: "); String name = scanner.nextLine();
        System.out.print("Total Amount: "); double total = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = scanner.nextLine();
        System.out.print("Status (Pending/Completed): "); String status = scanner.nextLine();

        sales.add(new Sale(id, name, total, date, status));
        System.out.println("✅ Added!");
    }

    // 9. VIEW SALES - 8 lines
    private void viewSales() {
        System.out.println("\n=== SALES ===");
        for (int i = 0; i < sales.size(); i++) {
            System.out.println((i+1) + ". " + sales.get(i));
        }
    }

    // 10. VIEW CUSTOMERS (DATABASE) - 3 lines
    private void viewCustomersFromDatabase() {
        System.out.println("\n=== CUSTOMERS (DATABASE) ===");
        customerDAO.getAllCustomers();
    }

    // 11. UPDATE CUSTOMER - 14 lines
    private void updateCustomer() {
        System.out.println("\n--- UPDATE CUSTOMER ---");

        System.out.print("Customer ID: "); String id = scanner.nextLine();
        System.out.print("New Name (or Enter to keep): "); String name = scanner.nextLine();
        System.out.print("New Total (or -1 to keep): "); double total = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("New Email (or Enter to keep): "); String email = scanner.nextLine();

        customerDAO.updateCustomer(id, name, total, email);
    }

    // 12. DELETE CUSTOMER - 3 lines
    private void deleteCustomer() {
        System.out.println("\n--- DELETE CUSTOMER ---");
        System.out.print("Customer ID: "); String id = scanner.nextLine();
        customerDAO.deleteCustomer(id);
    }

    // 13. SEARCH BY NAME - 5 lines
    private void searchCustomerByName() {
        System.out.println("\n--- SEARCH BY NAME ---");
        System.out.print("Enter name: "); String name = scanner.nextLine();
        List<Customer> results = customerDAO.searchByName(name);
        customerDAO.displayCustomerList(results);
    }

    // 14. SEARCH BY RANGE - 7 lines
    private void searchByPurchaseRange() {
        System.out.println("\n--- SEARCH BY RANGE ---");
        System.out.print("Min amount: "); double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Max amount: "); double max = scanner.nextDouble();
        scanner.nextLine();
        List<Customer> results = customerDAO.searchByPurchaseRange(min, max);
        customerDAO.displayCustomerList(results);
    }

    // 15. SEARCH HIGH SPENDERS - 5 lines
    private void searchHighSpenders() {
        System.out.println("\n--- SEARCH HIGH SPENDERS ---");
        System.out.print("Min purchase amount: "); double min = scanner.nextDouble();
        scanner.nextLine();
        List<Customer> results = customerDAO.searchByMinPurchase(min);
        customerDAO.displayCustomerList(results);
    }
}