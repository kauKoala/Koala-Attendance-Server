package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;
    private int solvedBaekjoon;
    private int writtenTistory;
    private int solvedBaekjoonWeek;
    private int writtenTistoryWeek;
    private Long studentId;
    private Long weekId;
}
