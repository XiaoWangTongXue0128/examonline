package com.duyi.examonline.service.impl;

import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.dao.ExamMapper;
import com.duyi.examonline.dao.StudentMapper;
import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
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

    @Autowired
    private StudentMapper studentMapper ;

    @Override
    public PageVO findByPage(int page, int rows, Map condition) {

        PageHelper.startPage(page, rows) ;
        List<Exam> exams = examMapper.find(condition);
        PageInfo info = new PageInfo(exams);

        //...

        return CommonUtil.pageCast(info,condition);
    }

    @Override
    public void save(Exam exam) {
        examMapper.insert(exam);
    }

    @Override
    public Exam findById(Long id) {
        return examMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Exam exam) {
        examMapper.updateByPrimaryKeySelective(exam);
    }

    @Override
    public List<Map<String, String>> findBindStudents(String className,String[] sidArray) {
        return studentMapper.findBindStudents(className,sidArray);
    }

    @Override
    public List<Map<String, String>> findUnbindStudents(String className, String[] sidBindArray) {
        return studentMapper.findUnbindStudents(className,sidBindArray);
    }
}
