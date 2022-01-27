package com.duyi.examonline.controller;

import com.duyi.examonline.common.BaseController;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.Question;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.Template;
import com.duyi.examonline.domain.vo.QuestionVO;
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
import java.util.ArrayList;
import java.util.Arrays;
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
    public String toAdd(Model model,HttpSession session){
        //现在因为完成了静态模板的试题添加（缓存）
        //所以在没有保存和取消之前，每次访问这个静态模板页时
        //需要将之前缓存的试题信息一同展示在网页上
        List<Question> questionCache = (List<Question>) session.getAttribute("questionCache");
        if(questionCache == null){
            //首次访问模板页面，还没有缓存，构建一个
            questionCache = new ArrayList<>() ;
        }
        //将question 转换成 questionVO
        List<QuestionVO> questions = new ArrayList<>();
        int index = 1 ;
        for(Question question : questionCache){
            QuestionVO questionVO = questionCast(question,index++) ;
            questions.add(questionVO) ;
        }
        model.addAttribute("questions",questions) ;


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

    @RequestMapping("/questionTemplate.html")
    public String toQuestionTemplate(){
        return "template/questionTemplate.html" ;
    }

    /**
     * 注意：缓存试题信息，并返回试题信息展示的图示模板
     * @param question
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/cacheQuestion")
    public String cacheQuestion(@RequestParam(name="index",defaultValue = "0") int index ,Question question,HttpSession session,Model model){
        if(index == 0){
            //添加考题
            //question缺少 course，status，tid
            question.setStatus(CommonData.DEFAULT_QUESTION_STATUS);
            Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
            question.setTid(teacher.getId());
        }

        //将这个question装入缓存
        //人为规定缓存就是session
        //人为规定session.key = questionCache
        List<Question> questionCache = (List<Question>) session.getAttribute("questionCache");
        if(questionCache == null){
            questionCache = new ArrayList<>();
            session.setAttribute("questionCache",questionCache);
        }
        if(index == 0){
            //添加考题，直接将其追加到缓存末尾
            questionCache.add(question) ;
        }else{
            //编辑考题，修改考题信息
            Question old_question = questionCache.get(index-1) ;
            old_question.setLevel( question.getLevel() );
            old_question.setSubject( question.getSubject() );
            old_question.setOptions( question.getOptions() );
            old_question.setAnswer( question.getAnswer() );

            question = old_question ;
        }

        //此时缓存完毕。需要回显。回显时需要组成VO对象
        index = index==0?questionCache.size():index ;
        QuestionVO questionVO = questionCast(question,index) ;

        //因为在toAdd方法中，每次访问模板页面，都需要携带之前缓存的试题信息，默认展示
        //从而网页模板中，需要对集合进行处理
        //此次单一试题缓存，利用的也是同一个模板
        //为了实现公用性，也需要将一个试题组成集合
        List<QuestionVO> questions = new ArrayList<>();
        questions.add(questionVO);
        model.addAttribute("questions",questions) ;

        return "template/questionViewTemplate::questionView" ;
    }

    @RequestMapping("/removeQuestion")
    @ResponseBody
    public void removeQuestion(int index,HttpSession session){
        List<Question> questionCache = (List<Question>) session.getAttribute("questionCache");
        questionCache.remove(index-1) ;
    }


    @RequestMapping("/removeQuestions")
    @ResponseBody
    public void removeQuestions(String indexes,HttpSession session){
        List<Question> questionCache = (List<Question>) session.getAttribute("questionCache");
        String[] indexArray = indexes.split(",");
        //因为ArrayList集合内部，删除某一个位置的元素后，后面的元素，会向前移动
        //从而接下来要删除的元素的位置就发生了变化
        //所以我们考虑从后向前删除  1,2,5
        //Arrays.sort(indexArray);
        for(int i=indexArray.length-1;i>=0;i--){
            int index = Integer.parseInt( indexArray[i] );
            questionCache.remove(index-1);
        }
    }

    @RequestMapping("/editQuestion")
    @ResponseBody
    public QuestionVO editQuestion(int index,HttpSession session){
        List<Question> questionCache = (List<Question>) session.getAttribute("questionCache");
        Question question = questionCache.get(index-1) ;

        QuestionVO questionVO = questionCast(question,index);
        return questionVO ;
    }








    private QuestionVO questionCast(Question question,int index){
        QuestionVO questionVO = new QuestionVO();
        //原来cache中有10条记录，当前这个就是第11个，序号也应该是11，恰好是cache.size()
        questionVO.setIndex( index );
        questionVO.setSubject( question.getSubject() );
        questionVO.setType( question.getType() );

        String[] optionArray = question.getOptions().split(CommonData.SPLIT_SEPARATOR);
        questionVO.setOptionList( Arrays.asList(optionArray) );

        String[] answerArray = question.getAnswer().split(CommonData.SPLIT_SEPARATOR);
        questionVO.setAnswerList( Arrays.asList(answerArray) );

        return questionVO ;
    }

}
