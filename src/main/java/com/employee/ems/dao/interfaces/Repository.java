package com.employee.ems.dao.interfaces;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<T> {

    public abstract T getEntityById(Class<T> projectClass, String id);

    public abstract Optional<Serializable> insertEntity(T entityObject);

    public abstract boolean updateEntity(T updatedEntityObject);

    public abstract boolean deleteEntity(Class<T> entityClass, String entityId);

}
