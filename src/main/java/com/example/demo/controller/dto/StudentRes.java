package com.example.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @Data
@AllArgsConstructor @NoArgsConstructor
public class StudentRes {

    @Schema(description = "id 값")
    public Long id;
    @Schema(description="학생 이름")
    public String name;

}
