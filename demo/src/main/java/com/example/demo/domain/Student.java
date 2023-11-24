package com.example.demo.domain;

import com.example.demo.controller.dto.StudentReq;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String baekjoonName;
    private String tistoryName;
    private Long semesterId;
    private Long studyId;

    @Builder
    public Student(StudentReq studentReq){
        this.name = studentReq.getName();
        this.baekjoonName = studentReq.getBaekjoonName();
        this.tistoryName = studentReq.getTistoryName();
        this.studyId = studentReq.getStudyId();
        this.semesterId = studentReq.getSemesterId();
    }

}
