package com.employee.ems.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity, ID> {

    public List<Entity> getAllEntities();

    public Optional<Entity> getEntityById();

    public Optional<Entity> insertEntity(ID entityId, Entity entityObject);

    public Optional<Entity> updateEntity(ID entityId, Entity updatedEntityObject);

    public Optional<Entity> deleteEntity(ID entityId);
}
