package com.employee.ems.dao.derivedRepositories;

import com.employee.ems.dao.EntityRepository;
import com.employee.ems.model.*;
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
public class EmployeeRepository extends EntityRepository<Employee> {

    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeRepository(EntityManagerFactory factory, JdbcTemplate jdbcTemplate) {
        super(factory);
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = factory.unwrap(SessionFactory.class);
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

    public boolean associateEmployeeWithProject(String projectId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Project project = session.get(Project.class, projectId);
            employee.assignProject(project);
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

    public boolean assignManagerToEmployee(String managerId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Employee manager = session.get(Employee.class, managerId);
            employee.setManager(manager);
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

    public boolean assignOfficeToEmployee(String officeId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Office office = session.get(Office.class, officeId);
            employee.setOffice(office);
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

    public boolean assignEmployeeTypeToEmployee(String employeeTypeId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            EmployeeType employeeType = session.get(EmployeeType.class, employeeTypeId);
            employee.setEmployeeType(employeeType);
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

    public boolean assignDepartmentToEmployee(String departmentId, String employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Department department = session.get(Department.class, departmentId);
            employee.setDepartment(department);
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
