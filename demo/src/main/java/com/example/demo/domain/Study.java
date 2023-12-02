package com.example.demo.domain;

import com.example.demo.controller.dto.StudyReq;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int semesterId; //Join 필요

    @Builder
    public Study(StudyReq studyReq){
        this.name = studyReq.getName();
        this.description = studyReq.getDescription();
        this.totalWeeks = studyReq.getTotalWeeks();
        this.semesterId = studyReq.getSemesterId();

    }

}
