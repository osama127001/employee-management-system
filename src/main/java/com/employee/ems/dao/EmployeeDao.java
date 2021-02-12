package com.employee.ems.dao;

import com.employee.ems.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeDao {

    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public EmployeeDao(
            EntityManagerFactory factory,
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Employee> insertIntoEmployee(UUID id, Employee employee) {
        return null;
    }



}
