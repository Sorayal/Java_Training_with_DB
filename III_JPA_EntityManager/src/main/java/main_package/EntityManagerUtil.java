package main_package;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class EntityManagerUtil {
    // should be only a Singleton instance
    private static EntityManagerUtil emu = null;
    private final EntityManager em;
    private final EntityManagerFactory emf;


    public static void initPersistenceUnit(String unitName) {
        if(emu == null) {
            emu = new EntityManagerUtil(unitName);
        }
    }


    public EntityManagerUtil getEntityManagerUtil() {
        return emu;
    }

    private EntityManagerUtil(String unitName) {
            emf = Persistence.createEntityManagerFactory(unitName);
            em = emf.createEntityManager();
    }

    /**
     * Get the Entity Manager
     * @return {@link EntityManager}
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * Get the Entity Manager Factory
     * @return {@link EntityManagerFactory}
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
