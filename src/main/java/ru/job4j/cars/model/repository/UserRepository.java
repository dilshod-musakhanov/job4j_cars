package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.*;

@AllArgsConstructor
public class UserRepository {
    private static final Logger LOG = Logger.getLogger(UserRepository.class.getName());
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in creating User: " + user, e);
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in updating User: " + user, e);
        } finally {
            session.close();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in deleting by userId: " + userId, e);
        } finally {
            session.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<User> usersSortedAsc = session.createQuery(
                    "FROM User AS u ORDER BY u.id ASC", User.class).list();
            transaction.commit();
            return usersSortedAsc;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding all User by order id", e);
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User as u WHERE u.id = :fId", User.class)
                            .setParameter("fId", id);
            transaction.commit();
            return query.uniqueResultOptional();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding User by id: " + id, e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        Transaction transaction = null;
        String likePattern = String.format("%%%s%%", key);
        try {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery(
                    "FROM User as u WHERE u.login LIKE :likePattern", User.class)
                            .setParameter("likePattern", likePattern).list();
            transaction.commit();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding User by like login: " + key, e);
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(
                            "FROM User as u WHERE u.login = :fLogin", User.class)
                    .setParameter("fLogin", login);
            transaction.commit();
            return query.uniqueResultOptional();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding User by login: " + login, e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
