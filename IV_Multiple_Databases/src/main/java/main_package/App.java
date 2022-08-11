package main_package;

import org.hibernate.Session;
import util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        // SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        HibernateUtil util = new HibernateUtil(0);

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        // session.createNativeQuery("").setParameter("")
        session.createQuery("");
        // session.save(myObject);
    }
}
