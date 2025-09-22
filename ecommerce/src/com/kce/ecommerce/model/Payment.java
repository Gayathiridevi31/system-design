package com.kce.ecommerce.model;
import java.util.Date;

public class Payment {
	private int paymentId;
    private int orderId;
    private double amount;
    private boolean success;
    private Date paymentDate;

    public Payment(int paymentId, int orderId, double amount, boolean success) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.success = success;
        this.paymentDate = new Date();
    }

    public int getPaymentId() { return paymentId; }
    public int getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public boolean isSuccess() { return success; }
    public Date getPaymentDate() { return paymentDate; }

    @Override
    public String toString() {
        return "Payment [ID=" + paymentId +
                ", OrderID=" + orderId +
                ", Amount=" + amount +
                ", Success=" + success +
                ", Date=" + paymentDate + "]";
    }
	

}
