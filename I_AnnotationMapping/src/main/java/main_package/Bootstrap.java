package main_package;

import org.hibernate.Session;

import java.util.Scanner;

public class Bootstrap {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Scanner getScanner() {
        return SCANNER;
    }



    public static void main(String[] args) {
        String configFile = setConfigFileForDB();
        // Used Try-with // getCurrentSession will close the session after commit!
        try (Session session = HibernateUtil.getSessionFactory(configFile).openSession()) {
            Student student = new Student("Julia", "Hannes", "Madrid");

            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String setConfigFileForDB() {
        Scanner scanner = getScanner();
        System.out.println("Choose Database: 1 for My SQL ; 2 for H2");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> "/hibernate_configs/hibernate.cfg.xml";
            case "2" -> "/hibernate_configs/hibernate-h2_testdb.cfg.xml";
            default -> throw new IllegalArgumentException("Not a valid choice");
        };
    }
}
