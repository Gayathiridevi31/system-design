package com.kce.ecommerce.model;
import java.util.Date;

public class Shipment {
	private int shipmentId;
    private int orderId;
    private String status;
    private Date shippedDate;

    public Shipment(int shipmentId, int orderId, String status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
        this.shippedDate = new Date();
    }

    public int getShipmentId() { return shipmentId; }
    public int getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public Date getShippedDate() { return shippedDate; }

    public void updateStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Shipment [ID=" + shipmentId +
                ", OrderID=" + orderId +
                ", Status=" + status +
                ", Date=" + shippedDate + "]";
    }

}
