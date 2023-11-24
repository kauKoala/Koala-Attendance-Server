package com.example.demo.controller;


import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

        @Autowired
        private final StudentService studentService;

        // 학생 생성
        @ResponseBody
        @PostMapping("/create")
        public ResponseTemplate<String> createStudent(@RequestBody StudentReq studentReq) {
            try {
                String name = studentService.createStudent(studentReq);
                return new ResponseTemplate<>(name);
            } catch (ResponseException e){
                return new ResponseTemplate<>((e.getStatus()));
            }
        }
}
