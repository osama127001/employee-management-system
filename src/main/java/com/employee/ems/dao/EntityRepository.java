package com.employee.ems.dao;

import com.employee.ems.dao.interfaces.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntityRepository<Entity, ID> implements GenericRepository<Entity, ID> {

    @Override
    public List<Entity> getAllEntities() {
        return null;
    }

    @Override
    public Optional<Entity> getEntityById() {
        return Optional.empty();
    }

    @Override
    public Optional<Entity> insertEntity(ID entityId, Entity entityObject) {
        return Optional.empty();
    }

    @Override
    public Optional<Entity> updateEntity(ID entityId, Entity updatedEntityObject) {
        return Optional.empty();
    }

    @Override
    public Optional<Entity> deleteEntity(ID entityId) {
        return Optional.empty();
    }
}
