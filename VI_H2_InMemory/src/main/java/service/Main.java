package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Aircraft;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = provideFactory(setPersistenceName());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Declaring variable
        Aircraft airbus;
        // find per Entitymanager and printing the found instance
        airbus = em.find(Aircraft.class, 1);
        System.out.println(airbus);

        em.close();
    }

    public static String setPersistenceName() {
        return PersistenceUnit.H2_MEMORY;
    }

    public static EntityManagerFactory provideFactory(String unitName) {
        return Persistence.createEntityManagerFactory(unitName);
    }
}
