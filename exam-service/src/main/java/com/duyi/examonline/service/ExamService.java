package com.duyi.examonline.service;

import com.duyi.examonline.domain.vo.PageVO;

import java.util.Map;

public interface ExamService {

    PageVO findByPage(int page , int rows , Map condition);

}
