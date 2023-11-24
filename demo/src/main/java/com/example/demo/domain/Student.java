package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String baekjoonName;
    private String tistoryName;
    private Long semesterId;
    private Long studyId;
}
