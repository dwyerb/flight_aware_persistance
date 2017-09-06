package net.muroc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.boot.registry.StandartServiceRegistry;

/**
 * Created by Brendan on 9/5/2017.
 */
public class fa_persistence {
    public static void main (String[] args)
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySetting(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
    }
}
