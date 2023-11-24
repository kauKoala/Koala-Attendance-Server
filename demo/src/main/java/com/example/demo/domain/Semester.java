package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Semester {

    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private String term;

}
