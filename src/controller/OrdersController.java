package controller;

import model.Customer;
import model.OrderDetail;
import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */

public class OrdersController {
    public static void addNewOrder(Orders orders){
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Orders.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orders);
            session.getTransaction().commit();
        }catch (Exception ex){
            System.out.println("\n\n-------------------- \nOrder Controller details");
            System.out.println(ex);
        }
    }

    public static List getAllOrder(){
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Order.class);
//        SessionFactory sessionFactory= configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Query query=session.createQuery("FROM Order");
//        List list = query.list();
//        session.getTransaction().commit();
//        return  list;
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orders.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Orders");
            List list = query.list();
            session.getTransaction().commit();
            return list;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

//    public static String getLastOrderId(){
////        SessionFactory sf =
////                new Configuration()
////                        .configure("hibernate.cfg.xml")
////                        .addAnnotatedClass(Orders.class)
////                        .buildSessionFactory();
////        Session session = sf.openSession();
////
////        CriteriaBuilder cb = session.getCriteriaBuilder();
////
////
////        //Beginning the Transaction
////        session.beginTransaction();
////
////
////        //Creating the CriteriaQuery of an 'Object' type to represent result of this query
////        CriteriaQuery crt1 = (CriteriaQuery) cb.createQuery(Object.class);
////        Root<Orders> root1 = crt1.from(Orders.class);
////
////        //Finding the average of a column mapping the property named "salary"
////        crt1.select(cb.avg(root1.get("orderId")));
////        Query query1 = session.createQuery((CriteriaDelete) crt1);
////
////        //Executing Query to get a particular record in an Object from UserInfo
////        Object average = query1.getSingleResult();
////
////        System.out.println("Average of salaries : " + average);
////        System.out.println();
////        //Committing the Transaction
////        session.getTransaction().commit();
////
////
////        //closing the session
////        session.close();
//        //Creating the CriteriaQuery of an 'Object' type to represent result of each query
//
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orders.class);
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        //Creating the CritetiaBuilder from the session object
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//
////        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
////        Session session = sessionFactory.openSession();
////        String minHql = "Select min(emp.salary) FROM Employee emp";
//        String maxHql = "Select orderId FROM Orders";
////        String countHql = "Select count(*) FROM Employee emp";
//
////        Query minQuery = session.createQuery(minHql);
//        Query maxQuery = session.createQuery(maxHql);
////        Query countQuery = session.createQuery(countHql);
////        System.out
////                .println("Minimum salary in list : " + minQuery.list().get(0));
//
//        System.out.println("......................................");
//
//        System.out.println("Maximum salary in list : " + maxQuery.list());
////        System.out.println("Number of Employee : " + countQuery.list().get(0));
//
//        return "D99";
//
//    }


}


