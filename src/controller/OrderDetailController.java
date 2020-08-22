package controller;

import model.Customer;
import model.OrderDetail;
import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class OrderDetailController {
    public static String getLastOrderId(){
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OrderDetail.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            String maxHql = "Select max(emp.orderId) FROM OrderDetail emp";
            Query maxQuery = session.createQuery(maxHql);

            String order_id = maxQuery.list().toString().replaceAll("[\\[\\](){}]","");
            int lastDigits = Integer.parseInt(order_id.split("[A-Z]")[1])+1;
            return "D"+String.format("%04d", lastDigits).substring(0, 4);
        }catch (Exception ex){
            System.out.println("Error in Oderdetail \n"+ex);
            return "D001";
        }

    }
}
