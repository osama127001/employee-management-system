package com.employee.ems.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "office")
public class Office implements Serializable {

    @Id
    private final UUID id;

    @Column(name = "name")
    private final String name;

    @Column(name = "capacity")
    private final Integer capacity;

    public Office(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("capacity") Integer capacity
    ) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
