package com.duyi.examonline.dao;

import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);


    List<Exam> find(Map condition) ;


}