package com.example.demo.viewcontroller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.*;
import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import com.example.demo.service.HistoryService;
import com.example.demo.service.SemesterService;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private final SemesterService semesterService;
    private final StudentService studentService;
    private final StudyService studyService;
    private final HistoryService historyService;

    public ViewController(SemesterService semesterService, StudentService studentService, StudyService studyService, HistoryService historyService){
        this.semesterService = semesterService;
        this.studentService = studentService;
        this.studyService =  studyService;
        this.historyService = historyService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<String> semesterList= semesterService.getAllSemester();

        model.addAttribute("semesterList", semesterList);
        return "list";
    }

    @RequestMapping("/studylist")
    public String studylist(@RequestParam("semesterName") String encodedSemesterName, Model model) throws ResponseException {
        String semesterName = null;
        try {
            semesterName = URLDecoder.decode(encodedSemesterName, "UTF-8");  //RESTFul 개선 필요

            String[] parts = semesterName.split(" ");

            if (parts.length == 2) {
                String year = parts[0];
                String semesterType = parts[1];
                Semester semester = semesterService.findByYearAndSemesterType(year, SemesterType.valueOf(semesterType));

                if (semester != null) {
                    List<StudyRes> studyList = studyService.getStudyList(semester.getId());
                    model.addAttribute("studyList", studyList);
                    model.addAttribute("selectedSemester", semesterName);
                    return "list";
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // 예외 처리
        }

        // 처리할 수 없는 경우 예외 처리 또는 다른 작업 수행
        return "error";
    }

    @RequestMapping("/history")
    public String historylist(@ModelAttribute HistoryReq historyReq, Model model){
        try{
            //히스토리를 찾는다
            List<HistoriesRes> historyResList = historyService.getHistoryList(1L); //일단 하드코딩
            model.addAttribute("historyResList",historyResList);
        } catch(ResponseException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "list";
    }

//    @RequestMapping("/attend-api")
//    public String attendapi(@ModelAttribute //학생리스트와 스터디, Model model) {
//        try {
//
//        } catch(){
//
//        }
//
//        return "attend";
//    }

@RequestMapping(value="/")
public String home() {
        return "index";
        }

@RequestMapping(value="/main")
public String main(Model model) {
        String year = semesterService.getCurrentYear();
        SemesterType semesterType = semesterService.getCurrentSemesterType();
        model.addAttribute("year", year);
        model.addAttribute("semesterType", semesterType);
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
        String name = studyService.createStudy(studyReq);
        model.addAttribute("message", name+" 스터디 등록 성공");
        } catch (ResponseException e){
        model.addAttribute("message", e.getMessage());
        }
        return "register";
        }

@RequestMapping(value="/attend")
public ModelAndView attend(ModelAndView modelAndView, Model model) throws ResponseException { //@ModelAttribute Long semesterId - 이후 추가

        ArrayList<StudentRes> studentResList = studentService.getStudentList();
        List<StudyRes> studyResList = studyService.getStudyList(1L);
        modelAndView.addObject("studentList",studentResList);
        modelAndView.addObject("studyList",studyResList);
        modelAndView.setViewName("attend");


        return modelAndView;
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