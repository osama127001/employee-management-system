package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class EmployeeRepository extends EntityRepository<EmployeeRepository> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
    }
}
