package com.kce.ecommerce.model;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public double getTotalPrice() { 
        return product.getPrice() * quantity; 
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem [Product=" + product.getName() +
                ", Quantity=" + quantity +
                ", Total=" + getTotalPrice() + "]";
    }
}
