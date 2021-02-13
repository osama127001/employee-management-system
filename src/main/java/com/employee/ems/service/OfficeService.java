package com.employee.ems.service;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfficeService {

    private final EntityRepository<Office> entityRepository;

    @Autowired
    public OfficeService(EntityRepository<Office> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public List<Office> getOffices() {
        return entityRepository.getAllEntities(Office.class);
    }

    public Office getOffice(String id) {
        return entityRepository.getEntityById(Office.class, id);
    }

    public Optional<Serializable> saveOffice(Office office) {
        return entityRepository.insertEntity(
                new Office(
                        UUID.randomUUID().toString(),
                        office.getName(),
                        office.getCapacity()
                )
        );
    }

    public boolean updateOffice(String id, Office office) {
        return entityRepository.updateEntity(id, new Office(
                id,
                office.getName(),
                office.getCapacity()
        ));
    }

    public boolean deleteOffice(String id) {
        return entityRepository.deleteEntity(Office.class, id);
    }
}
