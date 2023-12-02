package com.example.demo.domain;

import com.example.demo.controller.dto.SemesterReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    @Builder
    public Semester(SemesterReq semesterReq){
        this.year = semesterReq.getYear();
        this.semesterType = semesterReq.getSemesterType();
    }
}
