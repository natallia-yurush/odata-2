package by.nyurush.odata.repository.impl;

import by.nyurush.odata.entity.jpa.User;
import by.nyurush.odata.exception.DaoException;
import by.nyurush.odata.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static by.nyurush.odata.util.HibernateUtil.getSessionFactory;

public class UserRepositoryImpl implements UserRepository {

    private static final UserRepository instance = new UserRepositoryImpl();

    private UserRepositoryImpl() {

    }

    public static UserRepository getInstance() {
        return instance;
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot save user");
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot delete user");
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Cannot find all users");
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new DaoException("Cannot find user by id");
        }
    }

}
