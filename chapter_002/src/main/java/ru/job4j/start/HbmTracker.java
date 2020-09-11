package ru.job4j.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.models.Item;

import java.util.List;
import java.util.function.Function;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 06.09.2020.
 */
public class HbmTracker implements Store<Item>, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    /**
     * Add element in a DB.
     * @param item - item.
     * @return - item from DB.
     */
    @Override
    public Item add(Item item) {
        try {
            this.tx(
                    session -> session.save(item)
            );
        } catch (Exception e) {
            LOGGER.error("Failed to add element to the Database. Element = {}.", item);
        }
        return item;
    }

    /**
     * Update an element in a DB.
     *
     * @param item - item,
     * @return - result.
     */
    @Override
    public boolean replace(Item item) {
        boolean result = false;
        try {
            this.tx(
                    session -> {
                        final Query query = session.createQuery("update Item set description = :desc, name = :name where id = :id");
                        query.setParameter("desc", item.getDescription());
                        query.setParameter("name", item.getName());
                        query.setParameter("id", item.getId());
                        return query.executeUpdate();
                    }
            );
            result = true;
        } catch (Exception e) {
            LOGGER.error("Failed to update element to the Database. Element = {}.", item);
        }
        return result;
    }

    /**
     * Delete an element from DB.
     *
     * @param id - if of element.
     * @return - result.
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            this.tx(
                    session -> {
                        final Query query = session.createQuery("delete Item where id = :id");
                        query.setParameter("id", id);
                        return query.executeUpdate();
                    }
            );
            result = true;
        } catch (Exception e) {
            LOGGER.error("Failed to delete element to the Database. Id = {}.", id);
        }
        return result;
    }

    /**
     * Return all elements from DB.
     *
     * @return - List of items.
     */
    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    /**
     * Find all elements from DB with this name.
     *
     * @param key - name.
     * @return - List of elements.
     */
    @Override
    public List<Item> findByName(String key) {
        this.tx(
                session -> {
                    final Query query = session.createQuery("from Item i where i.name = :name");
                    query.setParameter("name", key);
                    return query.list();
                }
        );

        List<Item> list = this.tx(
                session -> session.createQuery("from Item i where i.description = " + key).list()
        );
        return list;
    }

    /**
     * Return an elements with this id from DB.
     *
     * @param id - id of element.
     * @return - item.
     */
    @Override
    public Item findById(int id) {
        List<Item> list = this.tx(
                session -> session.createQuery("from Item i where i.id = " + id).list()
        );
        return list != null ? list.get(0) : null;
    }

    /**
     * Close SessionFactory.
     */
    @Override
    public void close() {
        this.sf.close();
    }

    private <E> E tx(final Function<Session, E> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            E rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
