package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
}
