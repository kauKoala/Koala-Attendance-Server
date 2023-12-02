package com.example.demo.domain;

import com.example.demo.controller.dto.StudyReq;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "week")
    private List<Week> weeks = new ArrayList<>();

    @OneToMany(mappedBy = "semester")
    private List<Semester> semesters = new ArrayList<>();

    @Builder
    public Study(StudyReq studyReq){
        this.name = studyReq.getName();
        this.description = studyReq.getDescription();
        this.totalWeeks = studyReq.getTotalWeeks();
    }

}
