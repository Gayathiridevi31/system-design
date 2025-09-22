package com.kce.ecommerce.main;

import com.kce.ecommerce.service.OrderService;
import com.kce.ecommerce.exception.*;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderService service = new OrderService();
        int choice;

        do {
            System.out.println("\n=== E-Commerce Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Add Customer");
            System.out.println("3. Place Order");
            System.out.println("4. Make Payment");
            System.out.println("5. Ship Order");
            System.out.println("6. Request Return");
            System.out.println("7. Display Products");
            System.out.println("8. Print Invoice");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter product name: ");
                        String pname = sc.next();
                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter stock: ");
                        int stock = sc.nextInt();
                        service.addProduct(pname, price, stock);
                        break;

                    case 2:
                        System.out.print("Enter customer name: ");
                        String cname = sc.next();
                        System.out.print("Enter email: ");
                        String email = sc.next();
                        service.addCustomer(cname, email);
                        break;

                    case 3:
                        System.out.print("Enter customer ID: ");
                        int cid = sc.nextInt();
                        Map<Integer, Integer> items = new HashMap<>();
                        char more;
                        do {
                            System.out.print("Enter product ID: ");
                            int pid = sc.nextInt();
                            System.out.print("Enter quantity: ");
                            int qty = sc.nextInt();
                            items.put(pid, qty);
                            System.out.print("Add more products? (y/n): ");
                            more = sc.next().charAt(0);
                        } while (more == 'y');
                        service.placeOrder(cid, items);
                        break;

                    case 4:
                        System.out.print("Enter order ID: ");
                        int oid = sc.nextInt();
                        service.makePayment(oid);
                        break;

                    case 5:
                        System.out.print("Enter order ID: ");
                        oid = sc.nextInt();
                        service.shipOrder(oid);
                        break;

                    case 6:
                        System.out.print("Enter order ID: ");
                        oid = sc.nextInt();
                        System.out.print("Enter product ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Enter return quantity: ");
                        int rqty = sc.nextInt();
                        service.requestReturn(oid, pid, rqty);
                        break;

                    case 7:
                        service.displayProducts();
                        break;

                    case 8:
                        System.out.print("Enter order ID to print invoice: ");
                        int invoiceId = sc.nextInt();
                        service.printInvoice(invoiceId);
                        break;

                    case 9:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println(" Invalid choice.");
                }
            } catch (OutOfStockException | InvalidReturnException | PaymentNotDoneException e) {
                System.out.println(" Error: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error " + e.getMessage());
            }

        } while (choice != 9);

        sc.close();
    }
}
