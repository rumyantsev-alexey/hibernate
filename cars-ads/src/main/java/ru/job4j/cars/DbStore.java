package ru.job4j.cars;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Класс реализует интерфейс Store для hibernate-реализации
 * @param <K> дженерик класс сущностей используемых в этом проекте
 */
public class DbStore<K extends ProjectCars> implements Store<K> {

    private final SessionFactory factory = HibernateSessionFactory.getSessionFactory();
    private final String entityname;
    private final Class<K> fullclass;

    /**
     * Конструктор с определением сокращенного имени класса
     * @param genclass
     */
    public DbStore(Class<K> genclass) {
        fullclass = genclass;
        String[] names =  genclass.getName().split("\\.");
        entityname = names[names.length - 1];
    }

    public String getEntityname() {
        return entityname;
    }

    @Override
    public int add(K model) {
        return this.tx(
                session -> (Integer) session.save(model)
        );
    }

    @Override
    public boolean update(K model) {
        return this.tx(
                session -> {
                    boolean result = true;
                    try {
                        session.update(model);
                    } catch (HibernateException e) {
                        result = false;
                    }
                    return result;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    String qr = "DELETE FROM " + entityname + " WHERE id = :Id";
                    Query query = session.createQuery(qr);
                    query.setParameter("Id", id);
                    return query.executeUpdate() > 0;
                }
        );
    }

    @Override
    public ArrayList<K> findAll() {
        return this.tx(
                session -> {
                    String qr = "FROM " + entityname + " ORDER BY id";
                    Query query = session.createQuery(qr);
                    return (ArrayList<K>) query.list();
                }
        );
    }

    @Override
    public K findById(int id) {
        return this.tx(
                session -> {
                    return (K) session.get(fullclass, id);
                }
        );
    }

    @Override
    public int findIdByModel(K model) {
        int result = 0;
        ArrayList<K> temp = this.findAll();
        for (K one: temp) {
            if (one.equals(model)) {
                result = one.getId();
            }
        }
        return result;
    }

    protected <T> T tx(final Function<Session, T> command) {
        T result = null;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

}
