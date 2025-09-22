package com.kce.ecommerce.model;
import java.util.Date;

public class ReturnRequest {
private int returnId;
private int orderId;
private int productId;
private int quantity;
private boolean approved;
private Date requestDate;

public ReturnRequest(int returnId, int orderId, int productId, int quantity) {
    this.returnId = returnId;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.approved = false;
    this.requestDate = new Date();
}

public int getReturnId() { return returnId; }
public int getOrderId() { return orderId; }
public int getProductId() { return productId; }
public int getQuantity() { return quantity; }
public boolean isApproved() { return approved; }
public Date getRequestDate() { return requestDate; }

public void approve() { this.approved = true; }

@Override
public String toString() {
    return "ReturnRequest [ID=" + returnId +
            ", OrderID=" + orderId +
            ", ProductID=" + productId +
            ", Quantity=" + quantity +
            ", Approved=" + approved +
            ", Date=" + requestDate + "]";
}
}