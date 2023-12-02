package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;
    private int solvedBaekjoon;
    private int writtenTistory;
    private int solvedBaekjoonWeek;
    private int writtenTistoryWeek;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="week_id")
    private Week week;
}
