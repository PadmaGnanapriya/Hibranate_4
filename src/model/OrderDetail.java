package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */

@Entity
public class OrderDetail {
    @Id
    private String orderId;
    private String itemCode;
    private int orderQty;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String itemCode, int orderQty, double unitPrice) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setOrderQty(orderQty);
        this.setUnitPrice(unitPrice);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
