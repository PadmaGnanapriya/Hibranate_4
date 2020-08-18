package controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Item;
import model.Order;
import model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        String customerId = txtCustomerId.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int quentityOnHand = Integer.parseInt(txtQuentityOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String orderDate = txtOrderDate.getText();
        String customerName = txtCustomerName.getText();
//        String itemCode = cmbItemCode.getText();
        ArrayList<OrderDetail> orderList = null;

//        while(myTable.)

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
}
