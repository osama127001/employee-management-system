package com.employee.ems.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    private final String id;

    @Column(name = "name")
    private final String name;

    @Column(name = "description")
    private final String description;

    public Department(){
        this.id = null;
        this.name = null;
        this.description = null;
    }

    public Department(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
