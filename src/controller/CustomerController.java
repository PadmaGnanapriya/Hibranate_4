package controller;

import model.Customer;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class CustomerController {
    private static Cache HibernateUtil;

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

    public static boolean updateCustomer(Customer customer) {
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Customer.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public static Customer searchCustomer(String id){
        try{
        Transaction transaction = null;
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Customer.class)
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
//            System.out.println(customer.getName()+" "+customer.getAddress());
            session.getTransaction().commit();
            return customer;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean deleteCustomer(String id){
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Customer.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.remove(customer);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }


    public static List getAllCustomers(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory= configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Customer");
        List list = query.list();
        session.getTransaction().commit();
        return  list;

    }



}
