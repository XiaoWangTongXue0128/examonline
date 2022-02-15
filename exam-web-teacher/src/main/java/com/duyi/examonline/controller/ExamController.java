package com.duyi.examonline.controller;

import com.duyi.examonline.common.BaseController;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.domain.*;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.domain.vo.QuestionVO;
import com.duyi.examonline.domain.vo.TemplateFormVO;
import com.duyi.examonline.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService ;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private TeacherService teacherService ;
    @Autowired
    private TemplateService templateService ;
    @Autowired
    private QuestionService questionService ;
    @Autowired
    private StudentService studentService ;


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

    @RequestMapping("/templateDetail.html")
    public String toTemplateDetail(Long templateId,Model model){
        Template template = templateService.findById(templateId);

        //对template做一些处理，使得可以在页面进行展示。例如对question的拆分。
        TemplateFormVO vo = new TemplateFormVO(template);
        model.addAttribute("template",vo);


        //如果是静态模板，模板中存储着关联的考题信息

        if(vo.getType().equals("静态模板")) {
            //要将vo中存储的题号，变成考题
            List<QuestionVO> questions = new ArrayList<QuestionVO>();
            List<Integer> qids = new ArrayList<>();
            qids.addAll(vo.getQuestion1());
            qids.addAll(vo.getQuestion2());
            qids.addAll(vo.getQuestion3());
            qids.addAll(vo.getQuestion4());
            qids.addAll(vo.getQuestion5());
            int index = 1 ;
            for (Integer qid : qids) {
                Question question = questionService.findById(qid.longValue());

                //将question->questionVO 在面展示
                QuestionVO questionVO = CommonUtil.questionCast(question, index++);
                questions.add(questionVO);
            }
            model.addAttribute("questions", questions);
        }

        return "template/edit::#exam-use-template-info";
    }

    @RequestMapping("/selectClasses.html")
    public String toSelectClasses(){
        return "student/student::#exam-use-classes-students" ;
    }

    @RequestMapping("/bindClasses")
    @ResponseBody
    public void bindClasses(Long id , String classNames,HttpSession session){
        String cacheKey = "classesCache"+id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        if(classesCache == null){
            classesCache = new HashMap<>();
            session.setAttribute(cacheKey,classesCache);
        }


        log.debug("binding start:" + cacheKey + " " + classesCache);

        String[] classNameArray = classNames.split(",");
        for(String className : classNameArray){
            classesCache.put(className,"ALL");
        }

        log.debug("binding end:" + cacheKey + " " + classesCache);
    }

    @RequestMapping("/unbindClasses")
    @ResponseBody
    public void unbindClasses(Long id , String classNames,HttpSession session){
        String cacheKey = "classesCache"+id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);


        log.debug("unbinding start:" + cacheKey + " " + classesCache);

        String[] classNameArray = classNames.split(",");
        for(String className : classNameArray){
            classesCache.remove(className);
        }

        log.debug("unbinding end:" + cacheKey + " " + classesCache);
    }


    @RequestMapping("/bindStudent")
    @ResponseBody
    public void bindStudent(Long id,String className , Long studentId,HttpSession session){
        String cacheKey = "classesCache"+id;

        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        if(classesCache == null){
            classesCache = new HashMap<>();
            session.setAttribute(cacheKey,classesCache);
        }

        log.debug("binding start:" + cacheKey + " " + classesCache);

        String classNameInfo = classesCache.get(className) ;
        if(classNameInfo == null){
            //首次绑定这个班级，这个班级目前只有这一个学生。
            classesCache.put(className,String.valueOf(studentId));
        }else{
            //这个班级存在，增加这个学生
            classesCache.put(className, classNameInfo+","+studentId);
        }

        log.debug("binding end:" + cacheKey + " " + classesCache);

    }


    @RequestMapping("/unbindStudent")
    @ResponseBody
    public void unbindStudent(Long id,String className , Long studentId,HttpSession session){
        String cacheKey = "classesCache"+id;

        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);

        log.debug("unbinding start:" + cacheKey + " " + classesCache);

        String classNameInfo = classesCache.get(className) ;
        if(classNameInfo.equals("ALL")){
            //之前绑定了所有的学生，现在解绑一个，存储信息就需要从ALL ->1,2,4,5,6
            //需要先将ALL->完整的id字符串 1,2,3,4,5,6 -> 再移除 1,2,4,5,6
            String studentStr = studentService.findStudentIdsExcludeId(className, studentId);
            classesCache.put(className,studentStr);
        }else{
            //之前也是绑定一部分学生（1,2,3,4,5）
            List<String> list = Arrays.asList(classNameInfo.split(","));
            Set<String> set = new HashSet<>(list);
            set.remove( String.valueOf(studentId) );
            String studentStr = "" ;
            for(String sid:set){
                studentStr += sid + "," ;
            }
            studentStr = studentStr.substring(0,studentStr.length()-1);
            classesCache.put(className,studentStr);
        }

        log.debug("unbinding end:" + cacheKey + " " + classesCache);

    }

}
