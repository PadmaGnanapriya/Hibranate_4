package controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderForm {
    public TextField txtOrderId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQuentityOnHand;
    public TextField txtQty;
    public TextField txtOrderDate;
    public TextField txtCustomerName;
    public ComboBox cmbItemCode;
    public TextField txtTotal;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colCode;
    public TableView<OrderFromTable> myTable;
    public ComboBox cmbCustomerId;
    public Button AddButton;

    public void initialize() {


        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));

        genarateOrderDate();
        loadAllCustomers();
        loadAllItems();
        genarateOrderId();
    }

    private void genarateOrderDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        txtOrderDate.setText(dateFormat.format(date));
    }

    public void AddButtonPerform(ActionEvent actionEvent) {
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int priviousQty=0;
//        double total = ;

        for(int i=0; i<myTable.getItems().size();i++){
                   if(cmbItemCode.getValue().toString()==myTable.getItems().get(i).getItemCode()){
                priviousQty=myTable.getItems().get(i).getQty();
                myTable.getItems().remove(i);
            }
        }
//        int row = isAlreadyExist(cmbItemCode.getValue().toString());

//        if (row == -1) {
            OrderFromTable orderFromTable =new OrderFromTable(cmbItemCode.getValue().toString(),txtDescription.getText(),qty+priviousQty,unitPrice,unitPrice * (qty+priviousQty));
            myTable.getItems().add(orderFromTable);
            cmbItemCode.requestFocus();

//        } else {
////            qty += (int) dtm.getValueAt(row, 2);
////            total = qty * unitPrice;
////            txtItemDetails.setValueAt(qty, row, 2);
////            tblItemDetails.setValueAt(total, row, 4);
//        }
        cmbItemCode.requestFocus();
    }

    private int isAlreadyExist(String toString) {

//        cmbItemCode.getValue().toString()
        return -1;
    }

    public void RomovePerform(ActionEvent actionEvent) {
        ObservableList<OrderFromTable> selectedRows = myTable.getSelectionModel().getSelectedItems();
        ArrayList<OrderFromTable> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> myTable.getItems().remove(row));
    }

    public void PlaceOrderPerform(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int quentityOnHand = Integer.parseInt(txtQuentityOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String orderDate = txtOrderDate.getText();
        String customerName = txtCustomerName.getText();

        OrderFromTable orderFromTable=new OrderFromTable();
        List<List<String>> arrList=new ArrayList<>();

        for(int i=0; i<myTable.getItems().size();i++){
            orderFromTable=myTable.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(orderFromTable.getItemCode());
            arrList.get(i).add(orderFromTable.getDescription());
            arrList.get(i).add(String.valueOf(orderFromTable.getQty()));
            arrList.get(i).add(String.valueOf(orderFromTable.getPrice()));
        }
        String itemCodeT=null;
        String descriptionT=null;
        int qtyT=0;
        double unitPriceT=0;
        double fullTotal=0;

        for(int i=0; i<arrList.size();i++){
            for(int j=0;j<arrList.get(i).size();j++){
                itemCodeT=arrList.get(i).get(0);
                descriptionT=arrList.get(i).get(1);
                qtyT= Integer.parseInt(arrList.get(i).get(2));
                unitPriceT= Double.parseDouble(arrList.get(i).get(3));
            }
            OrderDetail orderDetail=new OrderDetail(txtOrderId.getText(),itemCodeT,qtyT,unitPriceT);
            ItemController itemController=new ItemController();
            ItemController.updateStock(orderDetail);
            OrderDetailController orderDetailController=new OrderDetailController();
            orderDetailController.addOrderDetail(orderDetail);
            fullTotal+= qtyT  * unitPriceT;
            txtTotal.setText(String.valueOf(fullTotal));
        }

        Orders orders=new Orders(txtOrderId.getText(),txtOrderDate.getText(),cmbCustomerId.getSelectionModel().toString(), Collections.unmodifiableList(arrList));
        OrdersController ordersController=new OrdersController();
        ordersController.addNewOrder(orders);

    }

    public void CustomerID_onAction(ActionEvent actionEvent) {
            String customerId = cmbCustomerId.getValue().toString();
            Customer customer = CustomerController.searchCustomer(customerId);
            txtCustomerName.setText(customer.getName());
    }

    private void loadAllCustomers() {
        List<Customer> cus=CustomerController.getAllCustomers();
        for (Customer customer : cus) {
            cmbCustomerId.getItems().add(customer.getId());
        }
    }

    private void loadAllItems(){
        List<Item> items=ItemController.loadAllItems();
        for (Item item : items) {
            cmbItemCode.getItems().add(item.getCode());
        }
    }

    public void AddCustomerPerform(ActionEvent actionEvent) {
    }

    public void ItemCode_onAction(ActionEvent actionEvent) {
        String itemCode=cmbItemCode.getValue().toString();
        Item item=ItemController.searchItem(itemCode);
        txtDescription.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQuentityOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtQty.setText("");
        txtQty.requestFocus();
    }

    private void genarateOrderId() {
        String order_id= OrderDetailController.getLastOrderId();
        txtOrderId.setText(order_id);
    }
    public void EnterQtyTextField(ActionEvent actionEvent) {
        AddButton.requestFocus();
    }
}
