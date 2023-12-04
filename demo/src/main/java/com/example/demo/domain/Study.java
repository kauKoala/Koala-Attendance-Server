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

    @OneToMany
    @JoinColumn(name ="study_id")
    private List<Week> weeks = new ArrayList<>();

    @Builder
    public Study(String name, String description, List studyWeeks) {
        this.name = name;
        this.description = description;
        this.weeks = studyWeeks;
    }
}
