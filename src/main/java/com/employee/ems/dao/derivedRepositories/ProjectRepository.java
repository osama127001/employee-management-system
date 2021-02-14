package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.Employee;
import com.employee.ems.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProjectRepository extends EntityRepository<Project> {

    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

    @Autowired
    public ProjectRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }


    @Transactional
    public List<Project> getAllEntities() {
        String sql = "SELECT * FROM project";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new Project(
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

    public boolean addEmployeeToProject(String projectId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Project project = session.get(Project.class, projectId);
            project.addEmployee(employee);
            tx.commit();
            return true;
        } catch (Exception exception) {
            tx.rollback();
            System.out.println(
                    exception.getMessage()
            );
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

}
