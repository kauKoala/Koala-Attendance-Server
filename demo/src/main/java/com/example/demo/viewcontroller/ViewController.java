package com.example.demo.viewcontroller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.controller.dto.StudyReq;
import com.example.demo.service.SemesterService;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    private final SemesterService semesterService;
    private final StudentService studentService;
    private final StudyService studyService;

    public ViewController(SemesterService semesterService, StudentService studentService, StudyService studyService){
        this.semesterService = semesterService;
        this.studentService = studentService;
        this.studyService =  studyService;

    }

    @RequestMapping("/list")
    public String list(Model model) {
        Long totalSemester = semesterService.getTotalSemesterCount();
        model.addAttribute("totalSemester", totalSemester);
        return "list";
    }

    @RequestMapping(value="/")
    public String home() {
        return "index";
    }

    @RequestMapping(value="/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value="/register")
    public String register() {
        return "register";
    }


    @RequestMapping(value="/student-register")
    public String studentregister(@ModelAttribute StudentReq studentReq, Model model) {
        try {
            String name = studentService.createStudent(studentReq);
            model.addAttribute("message", name+" 학생 등록 성공");
        } catch (ResponseException e){
            model.addAttribute("message", e.getMessage());
        }
        return "register";
    }

    @RequestMapping(value="/study-register")
    public String studyregister(@ModelAttribute StudyReq studyReq, Model model) {
        try {
            System.out.println(studyReq.studyWeeks);
            String name = studyService.createStudy(studyReq);
            model.addAttribute("message", name+" 스터디 등록 성공");
        } catch (ResponseException e){
            model.addAttribute("message", e.getMessage());
        }
        return "register";
    }

    @RequestMapping(value="/attend")
    public String attend() {
        return "attend";
    }

    @RequestMapping(value="/alert")
    public String alert() {
        return "alert";
    }

    @RequestMapping(value="/nav")
    public String nav() {
        return "nav";
    }
}
