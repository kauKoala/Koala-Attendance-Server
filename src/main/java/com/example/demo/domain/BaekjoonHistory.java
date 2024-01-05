package com.example.demo.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="history")
@Getter @Setter @AllArgsConstructor
public class BaekjoonHistory {
    @Id
    private Long id;
    private List<Integer> solvedBaekjoon = new ArrayList<>();

}
