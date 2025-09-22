package com.kce.ecommerce.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private boolean paid;
    private boolean shipped;
    private Date orderDate;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.paid = false;
        this.shipped = false;
        this.orderDate = new Date();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public boolean isPaid() {
        return paid;
    }

    public void markAsPaid() {
        this.paid = true;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void markAsShipped() {
        this.shipped = true;
    }

    @Override
    public String toString() {
        return "Order [ID=" + orderId +
                ", Customer=" + customer.getName() +
                ", Total=" + getTotalAmount() +
                ", Paid=" + paid +
                ", Shipped=" + shipped + "]";
    }
}