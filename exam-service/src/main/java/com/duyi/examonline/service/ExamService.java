package com.duyi.examonline.service;

import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.vo.PageVO;

import java.util.Map;

public interface ExamService {

    PageVO findByPage(int page , int rows , Map condition);

    /**
     * @param exam
     * @Throws DuplicateKeyException 当考试名称重复时抛出该异常
     */
    void save(Exam exam );


    Exam findById(Long id) ;

}
