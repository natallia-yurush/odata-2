package by.nyurush.odata.repository.impl;

import by.nyurush.odata.entity.jpa.Cat;
import by.nyurush.odata.entity.jpa.Dog;
import by.nyurush.odata.entity.jpa.Pet;
import by.nyurush.odata.entity.jpa.User;
import by.nyurush.odata.exception.DaoException;
import by.nyurush.odata.repository.PetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static by.nyurush.odata.util.HibernateUtil.getSessionFactory;

public class PetRepositoryImpl implements PetRepository {

    private static final PetRepository instance = new PetRepositoryImpl();

    private PetRepositoryImpl() {

    }

    public static PetRepository getInstance() {
        return instance;
    }

    @Override
    public Pet save(Pet pet) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(pet);
            transaction.commit();
            return pet;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot save pet");
        }
    }

    @Override
    public void delete(Pet pet) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(pet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot delete pet");
        }
    }

    @Override
    public List<Pet> findAll() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("from Pet", Pet.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Cannot find pets");
        }
    }

    @Override
    public Pet findById(Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Pet.class, id);
        } catch (Exception e) {
            throw new DaoException("Cannot find pet");
        }
    }

    @Override
    public List<Cat> findAllCats() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("from Cat", Cat.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Cannot find cats");
        }
    }

    @Override
    public List<Dog> findAllDogs() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("from Dog", Dog.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Cannot find dogs");
        }
    }

    @Override
    public List<Pet> findAllByUser(User user) {
        try (Session session = getSessionFactory().openSession()) {

            Query<Pet> q = session.createQuery("from Pet pet where pet.user.id=?1",
                    Pet.class);
            q.setParameter(1, user.getId());

            return q.getResultList();
        } catch (Exception e) {
            throw new DaoException("Cannot find pets by user");
        }
    }
}
