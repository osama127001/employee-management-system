package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.EmployeeType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeTypeRepository extends EntityRepository<EmployeeType> {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeTypeRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<EmployeeType> getAllEntities() {
        String sql = "SELECT * FROM employee_type";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new EmployeeType(
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
