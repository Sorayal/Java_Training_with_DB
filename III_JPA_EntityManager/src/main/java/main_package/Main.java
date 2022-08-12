package main_package;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Scanner getScanner() {
        return SCANNER;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = provideFactory(setPersistenceName());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("Please choose what you want do: 1 - Persist, 2 - Update, 3 - Delete");
        Scanner scanner = getScanner();
        boolean isFinished;
        do {
            String input = scanner.nextLine();
            isFinished = true;
            switch (input) {
                case "1":
                    Author maru = new Author();
                    maru.setFirstName("Maru");
                    maru.setLastName("Selia");
                    em.persist(maru);
                    em.getTransaction().commit();
                    break;
                case "2":
                    String cmd = "FROM Author a WHERE a.first_name = 'Maru'";
                    List<Author> request = em.createNamedQuery(cmd).getResultList();
                    maru = request.get(0);
                    //query.
                    //maru = em.find(Author.class, 1L);
                    //
                    maru.setFirstName("Mari");
                    em.getTransaction().commit();
                    break;
                case "3":
                    maru = em.find(Author.class, 1L);
                    em.remove(maru);
                    em.getTransaction().commit();
                    break;
                default:
                    System.out.println("Not a valid choice");
                    isFinished = false;
            }
        } while (!isFinished);
        //em.getTransaction().commit();
        em.close();
    }

    public static String setPersistenceName() {
        Scanner scanner = getScanner();
        System.out.println("Choose Database: 1 for My SQL ; 2 for H2");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> "mysql";
            case "2" -> "h2_memory";
            default -> throw new IllegalArgumentException("Not a valid choice");
        };
    }

    public static EntityManagerFactory provideFactory(String unitName) {
        return Persistence.createEntityManagerFactory(unitName);
    }

    /**
     * - persistence.xml erstellen
     * - Dependencies in Maven einbinden
     * - EntityManagerFactory und EntityManager generieren
     * - Entitäten erstellen
     * - DB und User einrichten
     */
}
