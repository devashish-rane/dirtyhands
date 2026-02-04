package com.devashish.dirtyhands.users;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class UserDetail {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String collegeName;

    public UserDetail() {
        // JPA requires a no-arg constructor
    }

    public UUID getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
