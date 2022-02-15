package com.duyi.examonline.controller;

import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.DictionaryService;
import com.duyi.examonline.service.ExamService;
import com.duyi.examonline.service.TeacherService;
import com.duyi.examonline.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService ;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private TeacherService teacherService ;
    @Autowired
    private TemplateService templateService ;

    @RequestMapping("/exam.html")
    public String toExam(Model model ,HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        Map condition = CommonData.DEFAULT_CONDITION ;
        condition.put("tid",teacher.getId());

        PageVO vo = examService.findByPage(CommonData.DEFAULT_PAGE,CommonData.DEFAULT_ROWS,condition);
        model.addAttribute("pageVO",vo);
        return "exam/exam" ;
    }

    @RequestMapping("/examGridTemplate.html")
    public String toExamGrid(int pageNo , @RequestParam Map condition,Model model,HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        condition.put("tid",teacher.getId());

        PageVO vo = examService.findByPage(pageNo,CommonData.DEFAULT_ROWS,condition);
        model.addAttribute("pageVO",vo);

        return "exam/exam::#part-2" ;
    }


    @RequestMapping("/form.html")
    public String toForm(Long id,Model model){
        if(id != null){
            //编辑时页面访问，需要获取原始数据
            Exam exam = examService.findById(id);
            model.addAttribute("exam",exam);
        }
        return "exam/form" ;
    }

    @RequestMapping("/save")
    @ResponseBody
    public boolean save(Exam exam , HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        exam.setTid( teacher.getId() );
        exam.setStatus(CommonData.DEFAULT_EXAM_STATUS);

        try{
            examService.save(exam);
            return true ;
        }catch (DuplicateKeyException e){
            return false ;
        }
    }

    @RequestMapping("/fill.html")
    public String toFill(Long id , Model model){
        Exam exam = examService.findById(id);
        model.addAttribute("exam",exam);
        return "exam/fill" ;
    }


    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Exam exam){
        try{
            examService.update(exam);
            return true;
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            return false ;
        }
    }

    @RequestMapping("/templateSelect.html")
    public String toTemplateSelect(HttpSession session,@RequestParam Map condition,Model model){
        //查询基本信息， 课程信息（过滤），分享老师（过滤），模板信息（展示）
        List<String> courses = dictionaryService.findCourses();
        model.addAttribute("courses",courses) ;

        //zzt  有 3个模板（301,302,303），将301分享给我，最终存储（301,1）
        //如何获得分享给当前我这个老师模板的那些老师  当前老师id  找到被分享的模板id，在根据模板id找到其所属的老师id
        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        List<Teacher> teachers = teacherService.findByShare(teacher.getId());
        model.addAttribute("teachers",teachers);

        //查当前模板信息
        condition.put("tid",teacher.getId());
        PageVO pageVO = templateService.find(CommonData.DEFAULT_PAGE, CommonData.DEFAULT_ROWS, condition);
        model.addAttribute("pageVO",pageVO);

        return "template/template::#exam-use-template" ;
    }

}
