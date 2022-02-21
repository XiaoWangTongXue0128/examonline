package com.duyi.examonline.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.duyi.examonline.common.BaseController;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.domain.*;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.domain.vo.QuestionVO;
import com.duyi.examonline.domain.vo.TemplateFormVO;
import com.duyi.examonline.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

    private static final String cacheKey_prefix = "classesCache" ;

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
    public String toFill(Long id , Model model,HttpSession session){
        String cacheKey = "classesCache"+id ;

        Exam exam = examService.findById(id);
        model.addAttribute("exam",exam);


        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        if(classesCache == null){
            classesCache = new HashMap<>();
            session.setAttribute(cacheKey,classesCache);

            //同时需要将持久化的关联信息取出，并装入缓存。

        }

        if(classesCache.size() > 0) {
            List<Map> refClasses = loadRefClasses(classesCache);
            model.addAttribute("refClasses", refClasses);
        }

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


    @RequestMapping("/loadCacheClasses")
    @ResponseBody
    public Map<String,String> loadCacheClasses(Long id , HttpSession session){
        String cacheKey = "classesCache"+id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        return classesCache;
    }

    @RequestMapping("/loadCacheStudents")
    @ResponseBody
    public String loadCacheStudents(Long id ,String className, HttpSession session){
        String cacheKey = "classesCache"+id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        return classesCache == null?"":classesCache.get(className);
    }


    @RequestMapping("/refClassGrid.html")
    public String toRefClassGrid(Long id,HttpSession session,Model model){
        String cacheKey = "classesCache"+id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);

        List<Map> refClasses = loadRefClasses(classesCache);

        model.addAttribute("refClasses",refClasses);

        return "exam/fill::#refClassGrid" ;
    }

    //班级信息展示处理，将缓存的班级信息转换成可以展示的班级信息（名称，总人数，关联人数）
    private List<Map> loadRefClasses(Map<String,String> classesCache){
        //先将所有缓存的班级变成一个字符串，利用逗号分隔
        //cs = "xxx-1班,xxx-2班,...."
        //sql = "select * from table where #{cs} like "xxx-1班"

        String classNames = "" ;
        Set<String> classNameSet = classesCache.keySet();
        for(String className : classNameSet){
            classNames += className + ",";
        }
        if(classNames.length() == 0){
            //没有任何缓存数据
            return null ;
        }
        classNames = classNames.substring(0,classNames.length()-1);
        List<Map> refClasses = studentService.findClassesByNames(classNames);

        //此时refClasses中只有2个字段 className，total。
        //但在前端展示时，还需要选择人数，这个信息就在缓存中。
        //还需要从缓存中获取这个选择人数的信息，并将其加入refClasses中
        for(Map refClass : refClasses){
            String className = (String) refClass.get("className");
            String info = classesCache.get(className);
            refClass.put("custom","N") ;
            if(info.equals("ALL")){
                //全选， 选择人数=总人数
                refClass.put("refTotal", refClass.get("total") ) ;
            }else{
                //只选了部分学生，这些学号用逗号连接
                refClass.put("refTotal",info.split(",").length ) ;
            }
        }

        //上述处理的都是在数据库中存在的班级
        //考试信息关联的班级中，还包括自定义的班级（数据库-student中不存在）
        //这一部分班级也需要处理，并且班级的总人数和关联人数相同
        for(String className : classNameSet){
            String info = classesCache.get(className);
            if(info.startsWith("x")){
                //这是一个自定义班级，需要手动处理。
                //经过上诉操作，这个班级并没有被存入refClasses
                Map map = new HashMap();
                map.put("className",className);
                map.put("custom","Y") ;
                String[] array = info.split(",");
                map.put("total",array.length-1);
                map.put("refTotal",array.length-1);
                refClasses.add(map);
            }
        }

        return refClasses;
    }


    @RequestMapping("/createClass.html")
    public String toCreateClass(){
        return "exam/createClass";
    }

    @RequestMapping("/createClass")
    @ResponseBody
    public boolean createClass(Long id , String className , HttpSession session){
        String cacheKey = cacheKey_prefix + id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        if(classesCache.get(className) != null){
            return false ;
        }

        //x,1,2,3,4,5
        classesCache.put(className,"x,") ;
        return true ;
    }


    @RequestMapping(value="/importClasses",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String importClasses(Long id,MultipartFile excel,HttpSession session) throws IOException {
        String cacheKey = cacheKey_prefix + id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);

        String msg = "" ;
        int count1 = 0 ;
        int count2 = 0 ;

        InputStream is = excel.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(is);

        reader.addHeaderAlias("学号","code") ;
        reader.addHeaderAlias("姓名","sname") ;

        List<String> sheetNames = reader.getSheetNames();
        for(int i=1;i<sheetNames.size();i++){
            String sheetName = sheetNames.get(i) ;
            //默认读取第一个sheet表。
            reader.setSheet(sheetName) ;

            List<Student> studentList = reader.readAll(Student.class);
            //此时读取了一个班级的学生信息
            //info存储班级学生的编号字符串
            String info = "" ;
            List<Student> existStudent = studentService.findExistStudent(studentList);
            for(Student student :existStudent){
                info += student.getId() + ",";
            }
            info = info.substring(0,info.length()-1);


            //判断当前班级是否存在。如果存在就正常拼装info，如果不存在，说明是一个自定义班级，需要以x开头
            if(!studentService.isExistClass(sheetName)){
                info = "x,"+info;
            }

            //不考虑缓存中已有该班级情况，如果存在，直接覆盖，以导入为主。
            classesCache.put(sheetName,info);

            //处理反馈问题。 处理存在和不存在学生
            for(Student student : studentList){
                //循环的是导入的学生（存在，保存在）
                if(existStudent.contains(student)){
                    //list集合的contains方法底层用equals比较是否相等。
                    //student默认equals比较地址。
                    //我们认为code和sname相等的就是相等。需要重写equals方法
                    count1++ ;
                }else{
                    msg += "【"+student.getCode()+"-"+student.getSname()+"】存储失败"+"|" ;
                    count2++ ;
                }
            }

        }

        msg = "共导入【"+(count1+count2)+"】学生|成功导入【"+count1+"】学生|失败导入【"+count2+"】学生|" + msg ;

        return msg ;
    }

    @RequestMapping("/removeRefClass")
    @ResponseBody
    public void removeRefClass(Long id , String className,HttpSession session){
        String cacheKey = cacheKey_prefix + id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);
        classesCache.remove(className);
    }



    @RequestMapping("/downStudentTemplate")
    public ResponseEntity<byte[]> downStudentTemplate() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("files/students2.xlsx") ;
        byte[] bs = new byte[is.available()];
        IOUtils.read(is,bs);

        HttpHeaders headers = new HttpHeaders() ;
        headers.add("content-disposition","attachment;filename=students2.xlsx");
        headers.add("content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return new ResponseEntity<byte[]>(bs,headers, HttpStatus.OK) ;
    }



    @RequestMapping(value="/importStudents",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String importStudents(Long id,String className , MultipartFile excel,HttpSession session) throws IOException {
        String cacheKey = cacheKey_prefix + id ;
        Map<String,String> classesCache = (Map<String, String>) session.getAttribute(cacheKey);

        String msg = "" ;
        int count1 = 0 ;
        int count2 = 0 ;

        InputStream is = excel.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(is);

        reader.addHeaderAlias("学号","code") ;
        reader.addHeaderAlias("姓名","sname") ;

        List<String> sheetNames = reader.getSheetNames();

        String sheetName = sheetNames.get(1) ;
        //默认读取第一个sheet表。
        reader.setSheet(sheetName) ;

        List<Student> studentList = reader.readAll(Student.class);
        //此时读取了一个班级的学生信息
        //info存储班级学生的编号字符串
        String info = classesCache.get(className);
        //此时追加后，info中可能会有重复的数据 info="1,2,4,5,1"
        //set可以自动去重。  info中的一堆id 装入set（自动去重），再重新组成info
        Set<String> idSet = new HashSet( Arrays.asList( info.split(",") ) );
        List<Student> existStudent = studentService.findExistStudent(studentList);
        for(Student student :existStudent){
            String sid = student.getId() + "";
            if(idSet.contains(sid)){
                continue ;
            }
            info += sid + "," ;
            idSet.add(sid); //防止后面的数据与当前这个新数据重复。
        }
        info = info.substring(0,info.length()-1);

        //不考虑缓存中已有该班级情况，如果存在，直接覆盖，以导入为主。
        classesCache.put(className,info);

        //处理反馈问题。 处理存在和不存在学生
        for(Student student : studentList){
            //循环的是导入的学生（存在，保存在）
            if(existStudent.contains(student)){
                //list集合的contains方法底层用equals比较是否相等。
                //student默认equals比较地址。
                //我们认为code和sname相等的就是相等。需要重写equals方法
                count1++ ;
            }else{
                msg += "【"+student.getCode()+"-"+student.getSname()+"】存储失败"+"|" ;
                count2++ ;
            }
        }


        msg = "共导入【"+(count1+count2)+"】学生|成功导入【"+count1+"】学生|失败导入【"+count2+"】学生|" + msg ;

        return msg ;
    }


    @RequestMapping("/adjustStudents.html")
    public String toAdjustStudents(Long id , String className , HttpSession session){


        return "exam/adjustStudents" ;
    }
}
