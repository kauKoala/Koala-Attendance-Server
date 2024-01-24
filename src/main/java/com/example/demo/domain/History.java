package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//객체의 필수적인 초기화 작업이 있기 때문에 생성자를 통해 아래에서 지정하지 않은 필드로 생성하는 방식 차단
//접근 권한을 Private로 하면 프록시 객체 생성에 문제가 생기게 된다.
@Getter @Setter
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solvedBaekjoonWeek;
    private int writtenTistoryWeek;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="week_id")
    private Week week;

    public History(Student student, Study study, Week week){
        this.student = student;
        this.study = study;
        this.week = week;
        this.solvedBaekjoonWeek = 0;
        this.writtenTistoryWeek = 0;
    }


    public void addTistoryWeek(int writtenTistoryWeek ){
        this.writtenTistoryWeek+=writtenTistoryWeek;
    }

}
