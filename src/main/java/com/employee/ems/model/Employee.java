package com.employee.ems.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    private final String id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "dob")
    private final LocalDateTime dob;

    @Column(name = "cv")
    private final String cv;

    @Column(name = "salary")
    private final double salary;

    @Column(name = "is_active")
    private final boolean isActive;

    @Column(name = "available_leave_days_per_year")
    private final Integer availableLeaveDaysPerYear;

    @Column(name = "available_sick_days_per_year")
    private final Integer availableSickDaysPerYear;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "office_id ")
    private Office office;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "department_id ")
    private Department department;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "work_position_id ")
    private EmployeeType employeeType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor_id")
    private Employee manager;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;


    public Employee() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.dob = null;
        this.cv = null;
        this.salary = 0.0d;
        this.isActive = false;
        this.availableLeaveDaysPerYear = null;
        this.availableSickDaysPerYear = null;
    }


    public Employee(
            @JsonProperty("id") String id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("dob") LocalDateTime dob,
            @JsonProperty("cv") String cv,
            @JsonProperty("salary") double salary,
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("availableLeaveDaysPerYear") Integer availableLeaveDaysPerYear,
            @JsonProperty("availableSickDaysPerYear") Integer availableSickDaysPerYear
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.cv = cv;
        this.salary = salary;
        this.isActive = isActive;
        this.availableLeaveDaysPerYear = availableLeaveDaysPerYear;
        this.availableSickDaysPerYear = availableSickDaysPerYear;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return isActive;
    }

    public Integer getAvailableLeaveDaysPerYear() {
        return availableLeaveDaysPerYear;
    }

    public Integer getAvailableSickDaysPerYear() {
        return availableSickDaysPerYear;
    }

    public void assignProject(Project project) {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects.add(project);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", cv='" + cv + '\'' +
                ", salary=" + salary +
                ", isActive=" + isActive +
                ", availableLeaveDaysPerYear=" + availableLeaveDaysPerYear +
                ", availableSickDaysPerYear=" + availableSickDaysPerYear +
                ", office=" + office +
                ", department=" + department +
                ", employeeType=" + employeeType +
                ", manager=" + manager +
                ", projects=" + projects +
                '}';
    }
}
