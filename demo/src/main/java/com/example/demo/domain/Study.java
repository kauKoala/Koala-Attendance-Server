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
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private int totalWeeks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="week_id")
    private List<Week> weeks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="semester_id")
    private List<Semester> semesters = new ArrayList<>();

    @Builder
    public Study(StudyReq studyReq){
        this.name = studyReq.getName();
        this.description = studyReq.getDescription();
        this.totalWeeks = studyReq.getTotalWeeks();
    }

}
