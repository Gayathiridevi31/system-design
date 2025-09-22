package com.kce.ecommerce.service;
import com.kce.ecommerce.model.*;
import com.kce.ecommerce.exception.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class OrderService {
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    private Map<Integer, Payment> payments = new HashMap<>();
    private Map<Integer, Shipment> shipments = new HashMap<>();
    private Map<Integer, ReturnRequest> returns = new HashMap<>();
    private int productCounter = 1;
    private int customerCounter = 101;
    private int orderCounter = 201;
    private int paymentCounter = 301;
    private int shipmentCounter = 401;
    private int returnCounter = 501;
    public void addProduct(String name, double price, int stock) {
        Product p = new Product(productCounter++, name, price, stock);
        products.put(p.getProductId(), p);
        System.out.println(" Product added: " + p);
    }
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println(" No products available.");
        } else {
            products.values().forEach(System.out::println);
        }
    }
    public void addCustomer(String name, String email) {
        Customer c = new Customer(customerCounter++, name, email);
        customers.put(c.getCustomerId(), c);
        System.out.println("Customer added: " + c);
    }
    public void placeOrder(int customerId, Map<Integer, Integer> productQuantities) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found.");
        }
        Order order = new Order(orderCounter++, customer);
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            Product product = products.get(entry.getKey());
            int qty = entry.getValue();
            if (product == null) {
                throw new IllegalArgumentException("Product not found (ID: " + entry.getKey() + ")");
            }
            if (product.getStock() < qty) {
                throw new OutOfStockException("Not enough stock for " + product.getName());
            }
            product.reduceStock(qty);
            OrderItem item = new OrderItem(product, qty);
            order.addItem(item);
        }
        orders.put(order.getOrderId(), order);
        System.out.println(" Order placed: " + order);
    }
    public void makePayment(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (order.isPaid()) {
            throw new IllegalStateException("Order already paid.");
        }

        Payment payment = new Payment(paymentCounter++, orderId, order.getTotalAmount(), true);
        order.markAsPaid();
        payments.put(payment.getPaymentId(), payment);

        System.out.println(" Payment successful: " + payment);
    }
    public void shipOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (!order.isPaid()) {
            throw new PaymentNotDoneException("Cannot ship. Payment not done.");
        }
        if (order.isShipped()) {
            throw new IllegalStateException("Order already shipped.");
        }

        Shipment shipment = new Shipment(shipmentCounter++, orderId, "Shipped");
        order.markAsShipped();
        shipments.put(shipment.getShipmentId(), shipment);

        System.out.println(" Order shipped: " + shipment);
    }
    public void requestReturn(int orderId, int productId, int qty) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        long days = ChronoUnit.DAYS.between(order.getOrderDate().toInstant(), new Date().toInstant());
        if (days > 7) {
            throw new InvalidReturnException("Return window expired (7 days).");
        }
        boolean found = false;
        Iterator<OrderItem> iterator = order.getItems().iterator();
        while (iterator.hasNext()) {
            OrderItem item = iterator.next();
            if (item.getProduct().getProductId() == productId) {
                found = true;
                if (qty > item.getQuantity()) {
                    throw new InvalidReturnException("Return quantity exceeds purchased.");
                }
                ReturnRequest rr = new ReturnRequest(returnCounter++, orderId, productId, qty);
                rr.approve();
                returns.put(rr.getReturnId(), rr);
                if (qty == item.getQuantity()) {
                    iterator.remove(); 
                } else {
                    item.setQuantity(item.getQuantity() - qty); 
                }
                item.getProduct().increaseStock(qty);
                System.out.println(" Return approved: " + rr);
                return;
            }
        }

        if (!found) throw new InvalidReturnException("Product not found in order.");
    }
    public void printInvoice(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println(" Order not found.");
            return;
        }

        System.out.println("\n Invoice ");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Order Date: " + order.getOrderDate());

        System.out.println("\nItems:");
        System.out.printf("%-15s %-5s %-10s\n", "Product", "Qty", "Total");

        if (order.getItems().isEmpty()) {
            System.out.println("No items (all returned).");
        } else {
            for (OrderItem item : order.getItems()) {
                System.out.printf("%-15s %-5d %-10.2f\n",
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice());
            }
        }

        System.out.printf("\nTotal Amount: %.2f\n", order.getTotalAmount());
        System.out.println("Payment Status: " + (order.isPaid() ? "Paid" : "Pending"));
        System.out.println("Shipping Status: " + (order.isShipped() ? "Shipped" : "Pending"));
        System.out.println("================\n");
    }
}
