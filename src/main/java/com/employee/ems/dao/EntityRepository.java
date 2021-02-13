package com.employee.ems.dao;

import com.employee.ems.dao.interfaces.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EntityRepository<T> implements GenericRepository<T> {

    private final SessionFactory sessionFactory;

    @Autowired
    public EntityRepository(EntityManagerFactory factory) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    @Transactional
    public List<T> getAllEntities(Class<T> entityClass) {
        String hql = "From " + entityClass.getSimpleName();
        try {
            return sessionFactory.openSession()
                    .createQuery(hql, entityClass)
                    .list();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public T getEntityById(Class<T> entityClass, String id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            return session.get(entityClass, id);
        } catch (Exception exp) {
            tx.rollback();
            exp.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Optional<Serializable> insertEntity(T entityObject) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Optional<Serializable> optionalSavedEntity = Optional.empty();
        try {
            tx = session.beginTransaction();
            optionalSavedEntity = Optional.ofNullable(session.save(entityObject));
            tx.commit();
        } catch (Exception exp) {
            tx.rollback();
            System.out.println(
                    exp.getMessage()
            );
            exp.printStackTrace();
        } finally {
            session.close();
        }
        return optionalSavedEntity;
    }

    @Override
    public boolean updateEntity(String entityId, T updatedEntityObject) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(updatedEntityObject);
            tx.commit();
            return true;
        } catch (Exception exp) {
            tx.rollback();
            System.out.println(
                    exp.getMessage()
            );
            exp.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteEntity(Class<T> entityClass, String entityId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(session.get(entityClass, entityId));
            tx.commit();
            return true;
        } catch (Exception exp) {
            tx.rollback();
            System.out.println(
                    exp.getMessage()
            );
            exp.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
