package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Student_Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="studentstudy_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


}
