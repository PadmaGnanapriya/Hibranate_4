package controller;

import model.Customer;
import model.Item;
import model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class ItemController {
    public static boolean addItem(Item item)  {
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Customer.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }

    }

    public static List loadAllItems()  {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Item.class);
        SessionFactory sessionFactory= configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Item");
        List list = query.list();
        session.getTransaction().commit();
        return  list;
    }

    public static Item searchItem(String itemCode)  {
        try{
            Transaction transaction = null;
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Item.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Item item = session.get(Item.class, itemCode);

            session.getTransaction().commit();
            return item;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static boolean updateStock(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
//        for (OrderDetail orderDetail : orderDetailList) {
//            boolean isUpdate=updateStock(orderDetail);
//            if(!isUpdate){
//                return false;
//            }
//        }
//        return true;
//    }
    public static boolean updateStock(OrderDetail orderDetail){
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update Item set qtyOnHand=qtyOnHand-? where code=?");
//        stm.setObject(1,orderDetail.getOrderQty());
//        stm.setObject(2,orderDetail.getItemCode());
//        return stm.executeUpdate()>0;
        Item item=searchItem(orderDetail.getItemCode());
        item.setQtyOnHand(item.getQtyOnHand()-orderDetail.getOrderQty());
        try {
            Configuration configuration = new Configuration();
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Item.class)
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

}
