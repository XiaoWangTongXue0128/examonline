package com.duyi.examonline.service.impl;

import com.duyi.examonline.dao.QuestionMapper;
import com.duyi.examonline.domain.Question;
import com.duyi.examonline.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper ;

    @Override
    public void save(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public Question findById(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Question question) {
        questionMapper.updateByPrimaryKeySelective(question);
    }
}
