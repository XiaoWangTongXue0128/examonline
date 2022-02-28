package com.duyi.examonline.controller;

import com.duyi.examonline.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller("/exam")
public class ExamController {

    @RequestMapping("/exam.html")
    public String toExam(HttpSession session){
        Student student = (Student) session.getAttribute("loginStudent");
        //获得当前这个学生的考试信息（默认当日）

        return "exam/exam" ;
    }
}
