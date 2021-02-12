package com.employee.ems.dao.interfaces;

import com.employee.ems.model.Project;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericRepository<T> {

    public List<T> getAllEntities(Class<T> projectClass);

    public Optional<T> getEntityById(Class<T> projectClass, UUID id);

    public Optional<Serializable> insertEntity(T entityObject);

    public Optional<T> updateEntity(UUID entityId, T updatedEntityObject);

    public Optional<T> deleteEntity(UUID entityId);

}
