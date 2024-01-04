package com.example.demo.domain;

import com.example.demo.controller.dto.SemesterRes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="semester_id")
    private Long id;
    private String year;
    private SemesterType semesterType; //1학기, 여름방학, 2학기, 겨울방학


    @OneToMany
    @JoinColumn(name="semester_id")
    private List<Study> studyList = new ArrayList<>();

    public Semester(String year, SemesterType semesterType){
        this.year = year;
        this.semesterType = semesterType;
    }

    public void addStudy(Study study){
        studyList.add(study);
    }
}
