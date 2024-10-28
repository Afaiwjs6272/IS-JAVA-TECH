package ru.Ukhanov.rest.dao;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.Ukhanov.rest.exception.NotFoundException;
import ru.Ukhanov.rest.model.cats.Cat;
import ru.Ukhanov.rest.model.owner.Owner;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Cat.class);
                configuration.addAnnotatedClass(Owner.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                throw new NotFoundException(e.getMessage());
            }
        }
        return sessionFactory;
    }
}