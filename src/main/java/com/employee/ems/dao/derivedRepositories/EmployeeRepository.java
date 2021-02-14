package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepository extends EntityRepository<Employee> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Employee> getAllEntities() {
        String sql = "SELECT * FROM employee";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new Employee(
                            resultSet.getString("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getDate("dob"),
                            resultSet.getString("cv"),
                            resultSet.getDouble("salary"),
                            resultSet.getBoolean("is_active"),
                            resultSet.getInt("available_leave_days_per_year"),
                            resultSet.getInt("available_sick_days_per_year")
                    )
            );
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }
}
