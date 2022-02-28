package com.duyi.examonline.controller;

import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.StudentExam;
import com.duyi.examonline.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
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


    @RequestMapping("/checkEnter")
    @ResponseBody
    public int checkEnter(Long examId , HttpSession session){
        Student student = (Student) session.getAttribute("loginStudent");

        Exam exam = examService.findById(examId);
        StudentExam studentExam = examService.findStudentExamById(student.getId(),examId);
        if(exam.getStatus().equals("丢弃")){
            //不常出现，学生在看到考试上瞬间，教师端就丢弃了考试。
            return 9 ;
        }


        if(studentExam.equals("已完成")){
            //考试可能未结束，考生已经完成考试
            return 8 ;
        }


        if(exam.getStatus().equals("已完成")){
            //考试结束
            return 7 ;
        }


        if(exam.getStartTime() != null){
            //设置了考试区间
            if(exam.getStartTime().after(new Date())){
                //还没有到考试时间
                return 6 ;
            }
        }

        //可以正常进入考试
        if(!studentExam.getStatus().equals("考试中")){
            //同时改变学生的考试状态（考试中）
            //如果是第一个进入考试的同学，考试的状态也需要变为考试中-正在进行
            examService.startExam(student.getId(),examId);
        }
        return 1;

    }
}
