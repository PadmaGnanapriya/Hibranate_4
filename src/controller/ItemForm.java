package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class ItemForm {
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQuentity;

    public void AddItem(ActionEvent actionEvent) {
        String itemCode=txtItemCode.getText();
        String description=txtDescription.getText();
        double unitPrice= Double.parseDouble(txtUnitPrice.getText());
        int quantity= Integer.parseInt(txtQuentity.getText());
        Item item=new Item(itemCode, description,unitPrice,quantity);

        try{
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Item.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
