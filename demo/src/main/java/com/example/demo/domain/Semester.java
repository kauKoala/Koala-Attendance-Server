package com.example.demo.domain;

import com.example.demo.controller.dto.SemesterReq;
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
    @GeneratedValue
    @Column(name="semester_id")
    private Long id;
    private int year;
    private SemesterType semesterType; //1학기, 여름방학, 2학기, 겨울방학


    @OneToMany
    @JoinColumn(name="semester_id")
    private List<Study> studyList = new ArrayList<>();

    @Builder
    public Semester(SemesterReq semesterReq){
        this.year = semesterReq.getYear();
        this.semesterType = semesterReq.getSemesterType();
    }
}
