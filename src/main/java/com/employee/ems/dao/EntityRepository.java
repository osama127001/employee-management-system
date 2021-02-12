package com.employee.ems.dao;

import com.employee.ems.dao.interfaces.GenericRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
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
    public Optional<T> getEntityById(Class<T> entityClass, UUID id) {
        try {
            return Optional.of(
                sessionFactory.openSession()
                        .get(entityClass, id)
            );
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Serializable> insertEntity(T entityObject) {
        try {
            return Optional.of(
                    sessionFactory.openSession()
                            .save(entityObject)
            );
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<T> updateEntity(UUID entityId, T updatedEntityObject) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<T> deleteEntity(UUID entityId) {
        return Optional.empty();
    }
}
