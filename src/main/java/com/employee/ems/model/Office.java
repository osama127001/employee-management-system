package com.employee.ems.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "office")
public class Office implements Serializable {

    @Id
    private final String id;

    @Column(name = "name")
    private final String name;

    @Column(name = "capacity")
    private final Integer capacity;

    public Office(){
        this.id = null;
        this.name = null;
        this.capacity = null;
    }

    public Office(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("capacity") Integer capacity
    ) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
