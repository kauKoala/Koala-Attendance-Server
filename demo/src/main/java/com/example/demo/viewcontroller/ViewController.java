package com.example.demo.viewcontroller;

import com.example.demo.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @Autowired
    private final SemesterService semesterService;
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

    @RequestMapping(value="/attend")
    public String attend() {
        return "attend";
    }
}
