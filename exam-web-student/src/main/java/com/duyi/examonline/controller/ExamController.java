package com.duyi.examonline.controller;

import com.duyi.examonline.common.BaseController;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.StudentExam;
import com.duyi.examonline.domain.vo.QuestionVO;
import com.duyi.examonline.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

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

    @RequestMapping("/page.html")
    public String toPage(Long examId , HttpSession session,Model model){
        //需要考试信息
        Exam exam = examService.findById(examId);

        //需要考生信息
        Student student = (Student) session.getAttribute("loginStudent");

        //需要试卷信息,读取文件，拆解文件内容，组成试题集合
        StudentExam studentExam = examService.findStudentExamById(student.getId(), examId);
        String pagePath = studentExam.getPagePath();
        pagePath = CommonData.PAGE_ROOT_PATH + pagePath ;
        List<QuestionVO> questions = readPage(pagePath);

        model.addAttribute("exam",exam);
        model.addAttribute("questions",questions);
        model.addAttribute("studentExam",studentExam);

        //还需要更新一下学生考试开始时间
        if(studentExam.getStartTime() == null) {
            examService.updateStartTime(student.getId(), examId);
            studentExam.setStartTime(new Date());
        }

        return "exam/page" ;
    }

    private List<QuestionVO> readPage(String pagePath){
        List<QuestionVO> questions = new ArrayList<>();
        try {

            FileReader r = new FileReader(pagePath);
            StringBuilder content = new StringBuilder( );
            char[] cs = new char[0x100] ;
            int length  ;
            while( (length = r.read(cs)) != -1){
                content.append(new String(cs,0,length));
            }

            log.debug("page info : \r\n {}" , content);

            String[] array = content.toString().split(CommonData.QUESTION_OPTION_SEPARATOR);

            log.debug("page array : \r\n {}", Arrays.toString(array));

            int index = 0 ;
            int no = 1 ;//题号
            QuestionVO question = null  ;
            for(String value : array){
                if(index == 0){
                    //一道新的考题
                    question = new QuestionVO() ;
                    question.setIndex(no++);
                    questions.add(question) ;
                    question.setType( value ) ;
                }else if(index == 1){
                    question.setScore( Integer.valueOf(value) );
                }else if(index == 2){
                    question.setSubject(value);
                }else if(index == 3){
                    question.setOptionList( Arrays.asList( value.split(CommonData.SPLIT_SEPARATOR)) );
                }else if(index == 4){
                    //value是答案
                    question.setAnswerList( Arrays.asList( value.split(CommonData.SPLIT_SEPARATOR) ) );
                }else if(index == 5){
                    //分隔符，当前这道题操作完毕
                    index = 0 ;
                    continue;
                }

                index++ ;

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions ;
    }

    @RequestMapping("/updateAnswer")
    @ResponseBody
    public void updateAnswer(@RequestParam Map answerInfo){
        examService.updateAnswer(answerInfo);
    }
}
