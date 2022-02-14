package com.duyi.examonline.service.impl;

import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.dao.ExamMapper;
import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.ExamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper ;

    @Override
    public PageVO findByPage(int page, int rows, Map condition) {

        PageHelper.startPage(page, rows) ;
        List<Exam> exams = examMapper.find(condition);
        PageInfo info = new PageInfo(exams);

        //...

        return CommonUtil.pageCast(info,condition);
    }
}
