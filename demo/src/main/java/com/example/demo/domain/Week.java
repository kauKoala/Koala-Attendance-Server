package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Week {
    @Id
    @GeneratedValue
    private Long id;
    private int weekNumber;
    private Long semesterId;

}
