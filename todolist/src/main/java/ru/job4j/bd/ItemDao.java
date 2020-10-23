package ru.job4j.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.Item;

import java.util.List;

public class ItemDao implements ItemDaoInterface {

    private Session currentSession;
    private Transaction currentTransaction;

    public ItemDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void save(Item item) {
        getCurrentSession().save(item);
    }

    @Override
    public void update(Item item) {
        getCurrentSession().update(item);
    }

    @Override
    public Item findById(int id) {
        return getCurrentSession().get(Item.class, id);
    }

    @Override
    public void delete(Item entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Item> findAll() {
        return getCurrentSession()
                .createQuery("from Item", Item.class).list();
    }

    public void deleteAll() {
        getCurrentSession()
                .createQuery("delete from Item", Item.class);
    }
}
