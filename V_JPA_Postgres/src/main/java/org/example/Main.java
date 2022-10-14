package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/**
 * Funktioniert nur mit Postgres.
 * Kommandos für flyway migration mvn flyway:migrate
 *
 * Info:
 * mvn flyway:info
 */

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Scanner getScanner() {
        return SCANNER;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = provideFactory(setPersistenceName());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
    }

    public static String setPersistenceName() {
        Scanner scanner = getScanner();
        System.out.println("Choose Database: 1 for My SQL ; 2 for H2 ; 3 for Postgres");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> "mysql";
            case "2" -> "h2_memory";
            case "3" -> "postgres";
            default -> throw new IllegalArgumentException("Not a valid choice");
        };
    }

    public static EntityManagerFactory provideFactory(String unitName) {
        return Persistence.createEntityManagerFactory(unitName);
    }
}