package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Aircraft;
import persistence.EntityManagerUtil;

import java.util.List;
import java.util.function.Consumer;

public class Main {



    public static void main(String[] args) {
        /*
        EntityManagerFactory emf = provideFactory(setPersistenceName());
        EntityManager em = emf.createEntityManager();
         */

        EntityManagerUtil emu = EntityManagerUtil.initPersistenceUnit(PersistenceUnit.H2_MEMORY);
        Consumer<EntityManager> consumer = entityManager -> entityManager.find(Aircraft.class, 1);
        emu.acceptTransactional(consumer);
        Aircraft craft = emu.applyTransactional(entityManager -> entityManager.find(Aircraft.class, 1));
        System.out.println(craft.getId());
        System.out.println(craft.getName());

        List<Aircraft> aircrafts = emu.select(Aircraft.class);

        // Testing with criteria builder
        System.out.println("\n\n");
        assert aircrafts != null;
        System.out.println(aircrafts.get(0).getName());


        //em.getTransaction().begin();

        // Declaring variable
        //Aircraft airbus;
        // find per Entitymanager and printing the found instance
        //airbus = em.find(Aircraft.class, 1);
        //System.out.println(airbus);

        //em.close();


    }

    /*
    public static String setPersistenceName() {
        return PersistenceUnit.H2_MEMORY;
    }

    public static EntityManagerFactory provideFactory(String unitName) {
        return Persistence.createEntityManagerFactory(unitName);
    }
    */

}
