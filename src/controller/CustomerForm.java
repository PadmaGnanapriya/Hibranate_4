package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerForm {
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void AddCustomer(ActionEvent actionEvent) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String address= txtAddress.getText();
        double salary= Double.parseDouble(txtSalary.getText());

        Customer customer=new Customer(customerId,customerName,address,salary);
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Customer.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex);
        }

    }


}
