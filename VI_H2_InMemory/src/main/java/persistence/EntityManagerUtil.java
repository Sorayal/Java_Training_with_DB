package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class EntityManagerUtil {
    // should be only a Singleton instance
    private static EntityManagerUtil emu = null;

    private final EntityManagerFactory emf;

    // static method to initialize the EntityManagerUtil with the correct persistence unit.
    // Calls constructor EntityManagerUtil(String unitName)
    public static EntityManagerUtil initPersistenceUnit(String unitName) {
        if(emu == null) {
            emu = new EntityManagerUtil(unitName);
        }
        return emu;
    }

    private EntityManagerUtil(String unitName) {
        emf = Persistence.createEntityManagerFactory(unitName);
        //em = emf.createEntityManager();
    }

    /**
     * Returns the EntityManagerUtil instance
     *
     * @return instance of {@link EntityManagerUtil}
     */
    public EntityManagerUtil getEntityManagerUtil() {
        return emu;
    }

    /**
     * Get the Entity Manager Factory
     * @return {@link EntityManagerFactory}
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }


    // That is used to perform single database transactions without expecting a result
    public void acceptTransactional (Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public <T> T applyTransactional(Function<EntityManager, T> function) {
        EntityManager em = emf.createEntityManager();
        T result = null;
        try{
            em.getTransaction().begin();
            result = function.apply(em);
            em.getTransaction().commit();
            return result;
        }catch(Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally{
            em.close();
        }
        return result;
    }


    /**
     * Select for Entities
     *
     * @param type type of the searched entities
     * @param <T> class type
     * @return a list of searched entities, returns empty if nothing was found.
     */
    public <T> List<T> select(Class<T> type) {
        List<T> result = Collections.emptyList();
        if(type == null) {
            return result;
        }
        CriteriaQuery<T> query = buildQuery(type);
        result = applyTransactional(entityManager -> entityManager.createQuery(query).getResultList());
        if (result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    // Select Query for Type
    private <T> CriteriaQuery<T> buildQuery(Class<T> type) {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> root = query.from(type);
        return query.select(root);
    }


    // One EntityManager per connection
    /*
    private final EntityManager em;

    public EntityManager getEm() {
        return em;
    }
     */
}
