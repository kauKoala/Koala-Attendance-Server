package com.example.demo.viewcontroller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.config.resTemplate.ResponseTemplate;
import com.example.demo.controller.dto.StudentReq;
import com.example.demo.service.SemesterService;
import com.example.demo.service.StudentService;
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

    public ViewController(SemesterService semesterService, StudentService studentService){
        this.semesterService = semesterService;
        this.studentService = studentService;
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
    public String register(@ModelAttribute StudentReq studentReq, Model model
    ) {
        try {
            String name = studentService.createStudent(studentReq);
            model.addAttribute("message", name+"등록 성공");
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
}
