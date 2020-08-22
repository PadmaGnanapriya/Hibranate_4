package model;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderFromTable {
    String itemCode;
    String description;
    int qty;
    double price;

    public OrderFromTable() {
    }

    public OrderFromTable(String itemCode, String description, int qty, double price, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    double total;




}
