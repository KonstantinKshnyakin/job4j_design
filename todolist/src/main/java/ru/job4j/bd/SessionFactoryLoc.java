package ru.job4j.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.function.Consumer;
import java.util.function.Function;

public class SessionFactoryLoc implements AutoCloseable {

    private SessionFactory sessionFactory;

    public SessionFactoryLoc() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Category.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    public <T> T makeSession(Function<Session, T> function) {
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            entity = function.apply(session);
            sessionFactory.close();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
        return entity;
    }

    public void makeTransaction(Consumer<Session> consumer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
