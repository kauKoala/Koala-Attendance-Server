package com.example.demo.domain;

import com.example.demo.controller.dto.StudentReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    @Column(name="student_id")
    private Long id;
    private String name;
    private String baekjoonName;
    private String tistoryName;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="study_id")
    private List<Study> study = new ArrayList<>(); //Join 필요

    @Builder
    public Student(StudentReq studentReq){
        this.name = studentReq.getName();
        this.baekjoonName = studentReq.getBaekjoonName();
        this.tistoryName = studentReq.getTistoryName();
        this.study = new ArrayList<>();
    }

}
