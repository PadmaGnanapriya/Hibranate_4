package controller;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerController {
    public static void addCustomer(Customer customer){
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
