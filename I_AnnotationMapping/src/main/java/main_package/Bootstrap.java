package main_package;

import org.hibernate.Session;

public class Bootstrap {
    public static void main(String[] args) {
        // Used Try-with
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = new Student("Julia", "Hannes", "Madrid");

            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
