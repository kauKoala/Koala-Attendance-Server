package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity @NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public History(Student student, Study study, Week week, int writtenTistoryWeek){
        this.student = student;
        this.study = study;
        this.week = week;
        this.writtenTistoryWeek = writtenTistoryWeek;
    }

}
