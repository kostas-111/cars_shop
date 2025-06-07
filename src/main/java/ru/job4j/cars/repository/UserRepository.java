package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.User;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        User result = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = user;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка при добавлении пользователя: {}", user, e);
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка обновления пользователя: {}", user, e);
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
        try {
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка при удалении пользовтеля с id: {}", userId, e);
        } finally {
            session.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        List<User> result = Collections.emptyList();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from User order by id", User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка поиска всех пользователей", e);
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = session.createQuery("from User where id = :fId", User.class)
                    .setParameter("fId", userId)
                    .uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка при поиске пользователя по id: {}", userId, e);
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
        List<User> result = Collections.emptyList();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from User where login like :fKey", User.class)
                    .setParameter("fKey", "%" + key + "%")
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка поиска пользователей по совпадениям в логине", e);
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = session.createQuery("from User where login = :fLogin", User.class)
                    .setParameter("fLogin", login)
                    .uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Ошибка при поиске пользователя по login: {}", login, e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
