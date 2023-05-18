package ru.job4j.cars.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.repository.CrudRepository;

public final class UtilRepository {
    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private static CrudRepository crudRepository = new CrudRepository(sf);

    private UtilRepository() {

    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

    public static CrudRepository getCrudRepository() {
        return crudRepository;
    }
}
