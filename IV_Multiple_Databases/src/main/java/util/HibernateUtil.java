package util;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final int NUMBER_OF_DATABASES = 2;
    private static SessionFactory[] sessionFactory = new SessionFactory[NUMBER_OF_DATABASES];
    private static Configuration[] configs = new Configuration[NUMBER_OF_DATABASES];

    private static final String[] configFiles = new String[]{("/hibernate_configs/hibernate-h2.cfg.xml"),
            ("hibernate_configs/hibernate.cfg.xml")};

    private int databaseChoice;
    private static final int H2 = 0;
    private static final int MySQL = 1;

    public HibernateUtil(int databaseChoice) {
        this.databaseChoice = databaseChoice;
    }

    public SessionFactory getSessionFactory() {
        if (null == sessionFactory[databaseChoice]) {
            try {
                // uses the config xml: hibernate.cfg.xml or hibernate-h2.cfg.xml which should be in class path
                configs[databaseChoice] = new Configuration().configure(configFiles[databaseChoice]);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure(configFiles[databaseChoice])
                        .applySettings(configs[databaseChoice].getProperties())
                        .build();
                //  This builds the Session Factory with the object serviceRegistry
                sessionFactory[databaseChoice] = new MetadataSources(serviceRegistry)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory[databaseChoice];
    }

    public Transaction startTransaction(){
        return getSessionFactory().getCurrentSession().beginTransaction();
    }


/*    public static SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            // uses the config xml: hibernate.cfg.xml which should be in class path
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
                    .build();

            //  This builds the Session Factory with the object serviceRegistry
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }*/
}
