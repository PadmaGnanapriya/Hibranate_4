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
        double total = unitPrice * qty;
        int row = isAlreadyExist(cmbItemCode.getValue().toString());

        if (row == -1) {
            OrderFromTable orderFromTable =new OrderFromTable(cmbItemCode.getValue().toString(),txtDescription.getText(),qty,unitPrice,total);
            myTable.getItems().add(orderFromTable);
        } else {
//            qty += (int) dtm.getValueAt(row, 2);
//            total = qty * unitPrice;
//            txtItemDetails.setValueAt(qty, row, 2);
//            tblItemDetails.setValueAt(total, row, 4);
        }

        cmbItemCode.requestFocus();
        calculateTotal();
    }

    private void calculateTotal() {
    }

    private int isAlreadyExist(String toString) {
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
        ArrayList<OrderDetail> orderList = null;

        String customerId=null;
        Orders orders =new Orders(orderId,orderDate,customerId,orderList);
        try{
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Orders.class)
                            .addAnnotatedClass(OrderDetail.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orders);
            session.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex);
        }
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
        //New button//This should be constructor


//        Item itm= new Item("111","Padme",33,34);
//        // Item Code, Description,Qty, Unit price,
//        myTable.getItems().add(itm);
//        myTable.setItems((ObservableList) itm);
;



        //Adding data to the table
//        ObservableList<String> list = FXCollections.observableArrayList();
//        table.setItems(data);
//        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        table.getColumns().addAll(fileNameCol, pathCol, sizeCol, dateCol);

    }

    public void ItemCode_onAction(ActionEvent actionEvent) {
        String itemCode=cmbItemCode.getValue().toString();
        Item item=ItemController.searchItem(itemCode);
        txtDescription.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQuentityOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    private void genarateOrderId() {
        String order_id= OrderDetailController.getLastOrderId();
        txtOrderId.setText(order_id);
    }


}
