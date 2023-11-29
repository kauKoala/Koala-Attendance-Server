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
    private int totalWeeks; //총 몇 주까지 진행?
    private int semesterId; //어떤 학기에 진행?

    @Builder
    public Study(StudyReq studyReq){
        this.name = studyReq.getName();
        this.description = studyReq.getDescription();
        this.totalWeeks = studyReq.getTotalWeeks();
        this.semesterId = studyReq.getSemesterId();

    }

}
