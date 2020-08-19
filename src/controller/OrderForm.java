package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderForm {
    public TextField txtOrderId;
    public TextField txtCustomerId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQuentityOnHand;
    public TextField txtQty;
    public TextField txtOrderDate;
    public TextField txtCustomerName;
    public ComboBox cmbItemCode;
    public Button AddCustomerPerform;
    public TextField txtTotal;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colCode;
    public TableView myTable;
    public ComboBox cmbCustomerId;
//
//    public OrderForm(){
//        loadAllCustomers();
//    }

    private void genarateOrderDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        txtOrderDate.setText(dateFormat.format(date));
    }



    public void AddButtonPerform(ActionEvent actionEvent) {

    }

    public void RomovePerform(ActionEvent actionEvent) {
    }

    public void PlaceOrderPerform(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
//        String customerId = cmbCustomerId.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int quentityOnHand = Integer.parseInt(txtQuentityOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String orderDate = txtOrderDate.getText();
        String customerName = txtCustomerName.getText();
//        String itemCode = cmbItemCode.getText();
        ArrayList<OrderDetail> orderList = null;

//        while(myTable.)

        String customerId=null;
        Order order=new Order(orderId,orderDate,customerId,orderList);

        try{
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Order.class)
                            .addAnnotatedClass(OrderDetail.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void CustomerID_onAction(ActionEvent actionEvent) {

    }

    private void loadAllCustomers() {
        List<Customer> cus=CustomerController.getAllCustomers();
        for (Customer customer : cus) {
            cmbCustomerId.getItems().add(customer.getId());
        }
    }

    public void AddCustomerPerform(ActionEvent actionEvent) {
        //New button//This should be constructor
        genarateOrderDate();

        loadAllCustomers();

//        Customer cus=new Customer("C002","Anjana","Kandy",85000);
//        CustomerController.updateCustomer(cus);  //           work properly

//        Customer cc= CustomerController.searchCustomer("C002");  // :- Anjana Kandy  ; work properly

//        CustomerController.deleteCustomer("C004"); // work properly

//
//

    }
}
