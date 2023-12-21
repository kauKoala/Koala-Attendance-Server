package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student_Study {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


}
