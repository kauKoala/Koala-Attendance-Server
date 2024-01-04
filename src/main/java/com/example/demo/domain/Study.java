package com.example.demo.domain;

import com.example.demo.controller.dto.StudyReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_id")
    private Long id;
    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name ="study_id")
    private List<Week> weeks = new ArrayList<>();

    @OneToMany(mappedBy = "study")
    private List<Student_Study> students = new ArrayList<>();

    @Builder
    public Study(String name, String description, List studyWeeks) {
        this.name = name;
        this.description = description;
        this.weeks = studyWeeks;
    }

}
