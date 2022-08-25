package main_package;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Bootstrap {
    public static void main(String[] args) {
        String connString = "jdbc:h2:file:./test_db";

            /*
            // JDBC Style
            Connection connection = DriverManager.getConnection(connString);
            System.out.println(connection);
            String sqlFile = "z5_Hibernate_Training\\src\\main\\java\\xml_mapping\\CreateTableStudent.sql";
            Path pathToFile = Paths.get(sqlFile);
            final String sql = String.join("\n", Files.readAllLines(pathToFile));
            System.out.println(sql);

            boolean hasResult = connection.createStatement().execute(sql);
            System.out.println(hasResult);
            */

        // Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // SessionFactory factory = HibernateUtil.getSessionFactory();
            // Session session = factory.openSession();
            Student student = new Student("Julia", "Hannes", "Madrid");

            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
            // session.close(); not necessary with Try-with
        } catch (Exception e) {
            e.printStackTrace();
        }
        //connection.close();;
    }
}
