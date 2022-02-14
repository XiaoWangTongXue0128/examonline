package com.duyi.examonline.controller;

import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService ;

    @RequestMapping("/exam.html")
    public String toExam(Model model ){
        PageVO vo = examService.findByPage(CommonData.DEFAULT_PAGE,CommonData.DEFAULT_ROWS,CommonData.DEFAULT_CONDITION);
        model.addAttribute("pageVO",vo);
        return "exam/exam" ;
    }

    @RequestMapping("/examGridTemplate.html")
    public String toExamGrid(int pageNo , @RequestParam Map condition,Model model){
        PageVO vo = examService.findByPage(pageNo,CommonData.DEFAULT_ROWS,condition);
        model.addAttribute("pageVO",vo);

        return "exam/exam::#part-2" ;
    }

}
