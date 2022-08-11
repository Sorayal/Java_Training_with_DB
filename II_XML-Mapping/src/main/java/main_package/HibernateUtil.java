package main_package;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    // Should be a singleton
    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            setupSessionFactory();
        }
        return sessionFactory;
    }


    private static void setupSessionFactory(){
        String configfile = "/hibernate_configs/hibernate-h2_testdb.cfg.xml";
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configfile)
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
