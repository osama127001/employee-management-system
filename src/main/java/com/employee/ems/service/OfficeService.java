package com.employee.ems.service;

import com.employee.ems.dao.derivedRepositories.OfficeRepository;
import com.employee.ems.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> getOffices() {
        return officeRepository.getAllEntities();
    }

    public Office getOffice(String id) {
        return officeRepository.getEntityById(Office.class, id);
    }

    public Optional<Serializable> saveOffice(Office office) {
        return officeRepository.insertEntity(
                new Office(
                        UUID.randomUUID().toString(),
                        office.getName(),
                        office.getCapacity()
                )
        );
    }

    public boolean updateOffice(String id, Office office) {
        return officeRepository.updateEntity(new Office(
                id,
                office.getName(),
                office.getCapacity()
        ));
    }

    public boolean deleteOffice(String id) {
        return officeRepository.deleteEntity(Office.class, id);
    }
}
