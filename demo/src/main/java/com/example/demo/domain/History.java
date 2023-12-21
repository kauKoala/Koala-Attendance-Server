package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> solvedBaekjoon = new ArrayList<>();
    @ElementCollection
    private List<Integer> writtenTistory = new ArrayList<>();;

    private int solvedBaekjoonWeek;
    private int writtenTistoryWeek;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="week_id")
    private Week week;
}
