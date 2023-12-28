package com.example.demo.viewcontroller;

import com.example.demo.config.resTemplate.ResponseException;
import com.example.demo.controller.dto.*;
import com.example.demo.domain.Semester;
import com.example.demo.domain.SemesterType;
import com.example.demo.service.HistoryService;
import com.example.demo.service.SemesterService;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudyService;
import org.apache.coyote.Response;
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
        List<SemesterRes> semesterList= semesterService.getAllSemester();

        model.addAttribute("semesterList", semesterList);
        return "list";
    }

    @RequestMapping("/studylist")
    public String studylist(@RequestParam("semesterId") Long semesterId, Model model) throws ResponseException {
        String semesterName = null;
        try {
            List<StudyRes> studyList = studyService.getStudyList(semesterId);
            model.addAttribute("studyList", studyList);
            model.addAttribute("selectedSemester", semesterName);
            return "list";
        } catch (ResponseException e) {
            model.addAttribute("message", e.getMessage());
        }

        // 처리할 수 없는 경우 예외 처리 또는 다른 작업 수행
        return "customerror";
    }

    @RequestMapping("/history")
    public String historylist(@RequestParam(value = "studyId", required = false) Long studyId,
                              Model model) {
        try {
            //히스토리를 찾는다
            List<HistoriesRes> historyResList = historyService.getHistoryList(studyId); //일단 하드코딩
            model.addAttribute("historyResList",historyResList);
        } catch(ResponseException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "list";
    }

    @RequestMapping("/attend-api")
    public String attendapi(@RequestParam(value = "action") String action,
                            @RequestParam(value = "student", required = false) List<Long> studentIds,
                            @RequestParam(value = "study", required = false) Long studyId,
                            Model model) {
        try {
            if (action.equals("post")) {
                if (studentIds != null && studyId != null) {
                    studentService.addStudyToStudyList(studentIds, studyId);
                }
            } else if (action.equals("delete")) {
                if (studentIds != null && studyId != null) {
                    studentService.removeStudyFromStudyList(studentIds, studyId);
                }
            }
            model.addAttribute("successMessage", "학생들 스터디 추가 성공");
        } catch (Exception e) {
            // 예외 처리
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "attend"; // 변경된 정보를 다시 보여주는 attend 페이지로 이동
        }
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
    public String attend(ModelAndView modelAndView, Model model) throws ResponseException { //@ModelAttribute Long semesterId - 이후 추가

        try {
            ArrayList<StudentRes> studentResList = studentService.getStudentList();
            List<StudyRes> studyResList = studyService.getStudyList(1L);
            model.addAttribute("studentList", studentResList);
            model.addAttribute("studyList", studyResList);
            modelAndView.setViewName("attend");
        } catch (ResponseException e){
            model.addAttribute("message", e.getMessage());
        }
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

    @RequestMapping(value = "/customerror")
    public String error() {
        return "customerror";
    }
    }