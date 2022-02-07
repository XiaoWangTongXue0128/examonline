package com.duyi.examonline.service;

import com.duyi.examonline.domain.Question;

public interface QuestionService {

    void save(Question question) ;

    Question findById(Long id);

}
