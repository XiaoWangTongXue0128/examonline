package com.duyi.examonline.controller;

import com.duyi.examonline.domain.Student;
import com.duyi.examonline.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService ;

    @RequestMapping("/exam.html")
    public String toExam(HttpSession session, Model model){
        Student student = (Student) session.getAttribute("loginStudent");
        //获得当前这个学生的考试信息（默认当日）
        List<Map> exams = examService.findByStudent(student.getId(),1) ;
        model.addAttribute("exams",exams) ;
        return "exam/exam" ;
    }

    @RequestMapping("/examGrid.html")
    public String toExamGrid(Integer timeFlag,HttpSession session,Model model){
        Student student = (Student) session.getAttribute("loginStudent");
        //获得当前这个学生的考试信息（默认当日）
        List<Map> exams = examService.findByStudent(student.getId(),timeFlag) ;
        model.addAttribute("exams",exams) ;
        return "exam/exam::#examGrid" ;
    }
}
