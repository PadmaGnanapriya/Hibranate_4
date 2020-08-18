import model.Customer;
import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class Demo {
//    public static void main(String[] args) {
//        Customer customer=new Customer("C002","Kasun","Galle",2200);
//        Item item=new Item("It01", "Car", 5000000,51);
//        try {
//            Configuration configuration = new Configuration();
////        SessionFactory sessionFactory=configuration.buildSessionFactory();
//            SessionFactory sessionFactory =
//                    new Configuration()
//                            .configure("hibernate.cfg.xml")
//                            .addAnnotatedClass(Customer.class)
//                            .addAnnotatedClass(Item.class)
//                            .buildSessionFactory();
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//            session.save(customer);
//            session.save(item);
//            session.getTransaction().commit();
//        }catch (Exception ex){
//            System.out.println(ex);
//        }
//    }
}
