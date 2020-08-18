import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class Demo {
    public static void main(String[] args) {
        Customer customer=new Customer("C001","Kamala","Galle",2200);
        try {
            Configuration configuration = new Configuration();
//        SessionFactory sessionFactory=configuration.buildSessionFactory();
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
