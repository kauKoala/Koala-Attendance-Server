package com.example.demo.controller.dto;

import com.example.demo.domain.Student;
import com.example.demo.domain.Study;
import com.example.demo.domain.Week;
import jakarta.persistence.*;
import lombok.*;

@Data @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HistoryRes {

    private Student student;

    private Study study;

    private Week week;

    private boolean isSolved; // 풀었는가?

    private boolean isWritten;
}
