package com.duyi.examonline.controller;

import com.duyi.examonline.common.BaseController;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.Template;
import com.duyi.examonline.service.DictionaryService;
import com.duyi.examonline.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService ;

    @Autowired
    private TemplateService templateService ;

    @RequestMapping("/add.html")
    public String toAdd(Model model){
        //查询，并携带课程信息
        List<String> courses = dictionaryService.findCourses();
        model.addAttribute("courses",courses) ;
        return "template/add" ;
    }


    @RequestMapping("/dynamic/save")
    @ResponseBody
    public boolean dynamicSave(@RequestParam Map<String,String> info, HttpSession session){
        log.debug("info [{}]",info);

        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");

        Template template = new Template();
        template.setType( info.get("type") );
        template.setName( info.get("name") );
        template.setTotalScore( Integer.parseInt(info.get("totalScore")) );
        template.setYl1( info.get("course") );
        template.setStatus( CommonData.DEFAULT_TEMPLATE_STATUS );
        template.setTid( teacher.getId() );

        //单选题处理
        String score1 = info.get("score1") ;
        String count11 = info.get("count11") ;
        String count12 = info.get("count12") ;
        String count13 = info.get("count13") ;
        String question1 = score1 + CommonData.SEPARATOR + count11 + CommonData.SEPARATOR + count12 + CommonData.SEPARATOR + count13 ;
        template.setQuestion1(question1);

        //多选题处理
        String score2 = info.get("score2") ;
        String count21 = info.get("count21") ;
        String count22 = info.get("count22") ;
        String count23 = info.get("count23") ;
        String question2 = score2 + CommonData.SEPARATOR + count21 + CommonData.SEPARATOR + count22 + CommonData.SEPARATOR + count23 ;
        template.setQuestion2(question2);

        //判断题处理
        String score3 = info.get("score3") ;
        String count31 = info.get("count31") ;
        String count32 = info.get("count32") ;
        String count33 = info.get("count33") ;
        String question3 = score3 + CommonData.SEPARATOR + count31 + CommonData.SEPARATOR + count32 + CommonData.SEPARATOR + count33 ;
        template.setQuestion3(question3);

        //填空题处理
        String score4 = info.get("score4") ;
        String count41 = info.get("count41") ;
        String count42 = info.get("count42") ;
        String count43 = info.get("count43") ;
        String question4 = score4 + CommonData.SEPARATOR + count41 + CommonData.SEPARATOR + count42 + CommonData.SEPARATOR + count43 ;
        template.setQuestion4(question4);

        //综合题处理
        String score5 = info.get("score5") ;
        String count51 = info.get("count51") ;
        String count52 = info.get("count52") ;
        String count53 = info.get("count53") ;
        String question5 = score5 + CommonData.SEPARATOR + count51 + CommonData.SEPARATOR + count52 + CommonData.SEPARATOR + count53 ;
        template.setQuestion5(question5);

        try{
            templateService.save(template);
            return true ;
        }catch (DuplicateKeyException e){
            return false ;
        }

    }
}
