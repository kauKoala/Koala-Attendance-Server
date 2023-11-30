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
    @RequestMapping("/jsp")
    public String jsp(Model model) throws Exception {
        Long totalSemester = semesterService.getTotalSemesterCount();
        model.addAttribute("totalSemester", totalSemester);
        return "list";
    }
}
