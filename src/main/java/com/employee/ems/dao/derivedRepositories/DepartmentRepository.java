package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DepartmentRepository extends EntityRepository<Department> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Department> getAllEntities() {
        String sql = "SELECT * FROM department";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new Department(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    )
            );
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

}
