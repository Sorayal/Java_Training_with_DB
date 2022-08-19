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
            setupSessionFactory(null);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(String configfile){
        if(configfile == null){
            configfile = "/hibernate_configs/hibernate-h2_testdb.cfg.xml";
        }
        if(sessionFactory == null){
            setupSessionFactory(configfile);
        }
        return sessionFactory;
    }


    // .addAnnotatedClass needs to be added to prevent unable to locate persister error
    private static void setupSessionFactory(String configfile){
        //String configfile = "/hibernate_configs/hibernate-h2_testdb.cfg.xml";
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configfile)
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
