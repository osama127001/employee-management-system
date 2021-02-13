package com.employee.ems.dao;

import com.employee.ems.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OfficeRepository extends EntityRepository<Office> {

    private final JdbcTemplate jdbcTemplate;

    public OfficeRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired


    @Transactional
    public List<Office> getAllEntities() {
        String sql = "SELECT * FROM office";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new Office(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("capacity")
                    )
            );
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

}
